package edu.wpi.cs4341.finalProject;

import edu.wpi.cs4341.finalProject.learningAI.ReversiPlayerIndividual;

import java.util.Optional;
import java.util.Random;


public class ReversiBoard {
    public static class Move {
        int i, j;

        public Move() {
        }

        public Move(int i, int j) {
            this.i = i;
            this.j = j;
        }
    };

    public enum TKind {nil, black, white; // don't change the order for any reason!
        public TKind getOponent(){
            switch(this){
                case black:
                    return white;
                case white:
                    return black;
                default:
                    throw new IllegalArgumentException("No opponent for type: " + this);
            }
        }

        private Optional<ReversiPlayerIndividual> currentIndividual = Optional.empty();
        public void setPlayer(ReversiPlayerIndividual individual){
            currentIndividual = Optional.of(individual);
        }
        public Optional<ReversiPlayerIndividual> getCurrentIndividual(){
            return currentIndividual;
        }
    };

    TKind[][] board = new TKind[8][8];
    int[] counter = new int[2]; // 0 = black, 1 = white
    boolean PassCounter;

    public ReversiBoard() {
        clear();
    }

    public TKind get(int i, int j) {
        return board[i][j];
    }

    public void set(Move move, TKind player) {
        switch (board[move.i][move.j]) {
            case white:
                counter[1]--;
                break;
            case black:
                counter[0]--;
                break;
        }
        board[move.i][move.j] = player;
        switch (player) {
            case white:
                counter[1]++;
                break;
            case black:
                counter[0]++;
                break;
        }
    }

    public int getCounter(TKind player) {
        return counter[player.ordinal() - 1];
    }

    public void clear() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = TKind.nil;
        board[3][4] = TKind.black;
        board[4][3] = TKind.black;
        board[3][3] = TKind.white;
        board[4][4] = TKind.white;
        counter[0] = 2;
        counter[1] = 2;
        PassCounter = false;
    }

    public String toString(){
        StringBuilder string = new StringBuilder("Board:\n");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                string.append(board[i][j] + " ");
            string.append("\n");
        }
        return string.toString();
    }

    public void println() {
        System.out.print("[");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(board[i][j] + ",");
            System.out.println((i == 7 ? "]" : ""));
        }
    }

    public int move(Move move, TKind kind) {
        return checkBoard(move, kind);
    }

    public boolean gameEnd() {
        return counter[0] + counter[1] == 64;
    }

    private int Check(Move move, int incx, int incy, TKind kind, boolean set) {
        TKind opponent;
        int x = move.i;
        int y = move.j;
        if (kind == TKind.black) opponent = TKind.white;
        else opponent = TKind.black;
        int n_inc = 0;
        x += incx;
        y += incy;
        while ((x < 8) && (x >= 0) && (y < 8) && (y >= 0) && (board[x][y] == opponent)) {
            x += incx;
            y += incy;
            n_inc++;
        }
        if ((n_inc != 0) && (x < 8) && (x >= 0) && (y < 8) && (y >= 0) && (board[x][y] == kind)) {
            if (set)
                for (int j = 1; j <= n_inc; j++) {
                    x -= incx;
                    y -= incy;
                    set(new Move(x, y), kind);
                }
            return n_inc;
        } else return 0;
    }

    private int checkBoard(Move move, TKind kind) {
        // check increasing x
        int j = Check(move, 1, 0, kind, true);
        // check decreasing x
        j += Check(move, -1, 0, kind, true);
        // check increasing y
        j += Check(move, 0, 1, kind, true);
        // check decreasing y
        j += Check(move, 0, -1, kind, true);
        // check diagonals
        j += Check(move, 1, 1, kind, true);
        j += Check(move, -1, 1, kind, true);
        j += Check(move, 1, -1, kind, true);
        j += Check(move, -1, -1, kind, true);
        if (j != 0) set(move, kind);
        return j;
    }

    private boolean isValid(Move move, TKind kind) {
        // check increasing x
        if (Check(move, 1, 0, kind, false) != 0) return true;
        // check decreasing x
        if (Check(move, -1, 0, kind, false) != 0) return true;
        // check increasing y
        if (Check(move, 0, 1, kind, false) != 0) return true;
        // check decreasing y
        if (Check(move, 0, -1, kind, false) != 0) return true;
        // check diagonals
        if (Check(move, 1, 1, kind, false) != 0) return true;
        if (Check(move, -1, 1, kind, false) != 0) return true;
        if (Check(move, 1, -1, kind, false) != 0) return true;
        if (Check(move, -1, -1, kind, false) != 0) return true;
        return false;
    }

    private int strategy(TKind me, TKind opponent) {
        int tstrat = 0;
        for (int i = 0; i < 8; i++)
            if (board[i][0] == opponent)
                tstrat++;
            else if (board[i][0] == me)
                tstrat--;
        for (int i = 0; i < 8; i++)
            if (board[i][7] == opponent)
                tstrat++;
            else if (board[i][7] == me)
                tstrat--;
        for (int i = 0; i < 8; i++)
            if (board[0][i] == opponent)
                tstrat++;
            else if (board[0][i] == me)
                tstrat--;
        for (int i = 0; i < 8; i++)
            if (board[7][i] == opponent)
                tstrat++;
            else if (board[7][i] == me)
                tstrat--;
        return tstrat;
    }

    private class resultFindMax {
        int max, numberBlack, numberWhite;
    }

    public int getFitness(TKind player) {
        int score = this.getCounter(player);
        if (gameEnd() || (!userCanMove(TKind.black) && !userCanMove(TKind.white))) { // No moves left
            if (counter[0] > counter[1]) {
                //System.out.println("Black Wins");
                if (player.equals(TKind.black)) {
                    return score + countEmptyTiles(); // score = number of pieces + empty tiles remaining
                } else
                    return 0;
            }

            else if (counter[0] < counter[1]) {
                //System.out.println("White Wins");
                if (player.equals(TKind.white)) {
                    return score + countEmptyTiles(); // score = number of pieces + empty tiles remaining
                } else
                    return 0;
            }

            else {
                //System.out.println("Draw ");
                return score;
            }
        }
        else {
            throw new IllegalStateException("Game has not ended");
        }
    }

    private int countEmptyTiles() {
        int numberTilesEmpty = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if ((board[i][j] == TKind.nil))
                    numberTilesEmpty++;
        return numberTilesEmpty;
    }

    private resultFindMax FindMax(int level, TKind me, TKind opponent) {
        int min, score, tempNumberBlack, tempNumberWhite;
        TKind[][] TempBoard = new TKind[8][8];
        int[] TempCounter = new int[2];
        resultFindMax findMaxResult = new resultFindMax();
        level--;
        findMaxResult.numberBlack = counter[0];
        findMaxResult.numberWhite = counter[1];
        for (int i = 0; i < 8; i++)
            System.arraycopy(board[i], 0, TempBoard[i], 0, 8);
        System.arraycopy(counter, 0, TempCounter, 0, 2);
        min = 10000;  // high value

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if ((board[i][j] == TKind.nil) && (checkBoard(new Move(i, j), me) != 0)) {
                    if (level != 0) {
                        resultFindMax tempFindMaxResult = FindMax(level, opponent, me);
                        tempNumberBlack = tempFindMaxResult.numberBlack;
                        tempNumberWhite = tempFindMaxResult.numberWhite;
                        score = tempFindMaxResult.max;
                    } else {
                        tempNumberBlack = counter[0];
                        tempNumberWhite = counter[1];
                        final int strategyValue;
                        if(me.getCurrentIndividual().isPresent()){
                            strategyValue = me.getCurrentIndividual().get().getWeightForBoardIndex(i, j);
                        } else {
                            strategyValue = strategy(me, opponent);
                        }
                        score = counter[opponent.ordinal() - 1] - counter[me.ordinal() - 1] + strategyValue;
                    }
                    if (min > score) {
                        min = score;
                        findMaxResult.numberBlack = tempNumberBlack;
                        findMaxResult.numberWhite = tempNumberWhite;
                    }
                    for (int k = 0; k < 8; k++)
                        System.arraycopy(TempBoard[k], 0, board[k], 0, 8);
                    System.arraycopy(TempCounter, 0, counter, 0, 2);
                }
        findMaxResult.max = -min;
        return findMaxResult;
    }


    public boolean findMove(TKind player, int llevel, Move move) {
        TKind[][] TempBoard = new TKind[8][8];
        int[] TempCounter = new int[2];
        int numberBlack, numberWhite, min, n_min;
        boolean found;
        resultFindMax findMaxResult = new resultFindMax();
        Random random = new Random();

        if (counter[0] + counter[1] >= 52 + llevel) { //
            llevel = counter[0] + counter[1] - 52;
            if (llevel > 5) llevel = 5;
        }

        for (int i = 0; i < 8; i++)
            System.arraycopy(board[i], 0, TempBoard[i], 0, 8);
        System.arraycopy(counter, 0, TempCounter, 0, 2);
        found = false;
        min = 10000;  // high value
        n_min = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if ((board[i][j] == TKind.nil) && (checkBoard(new Move(i, j), player) != 0)) {
                    if (player == TKind.black)
                        findMaxResult = FindMax(llevel - 1, TKind.white, player);
                    else findMaxResult = FindMax(llevel - 1, TKind.black, player);
                    if ((!found) || (min > findMaxResult.max)) {
                        min = findMaxResult.max;
                        numberWhite = findMaxResult.numberWhite;
                        numberBlack = findMaxResult.numberBlack;
                        move.i = i;
                        move.j = j;
                        found = true;
                    } else if (min == findMaxResult.max) { // RANDOM MOVE GENERATOR
                        n_min++;
                        if (random.nextInt(n_min) == 0) {
                            numberWhite = findMaxResult.numberWhite;
                            numberBlack = findMaxResult.numberBlack;
                            move.i = i;
                            move.j = j;
                        }
                    }
                    //             if found
                    //             then PreView(numberWhite,numberBlack);
                    for (int k = 0; k < 8; k++)
                        System.arraycopy(TempBoard[k], 0, board[k], 0, 8);
                    System.arraycopy(TempCounter, 0, counter, 0, 2);
                }
            }
        }
        return found;
    }

    public boolean userCanMove(TKind player) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if ((board[i][j] == TKind.nil) && isValid(new Move(i, j), player)) return true;
        return false;
    }

}
