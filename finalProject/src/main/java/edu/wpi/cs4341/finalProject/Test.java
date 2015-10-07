package edu.wpi.cs4341.finalProject;
/*
Test program for Reversi

Usage:  java Test

version 0.1, 10-12-2006

Requirement: Java 1.5 or later

*/


public class Test {
    public static void main(String[] args) {
        ReversiBoard board = new ReversiBoard();
        Move move = new Move();
        int n = 10;
        int win = 0;
        int drawn = 0;
        for (int i = 0; i < n; i++) {
            board.clear();
            while (board.userCanMove(TKind.black) || board.userCanMove(TKind.white)) {
                if (board.findMove(TKind.black, 4, move))
                    board.move(move, TKind.black);
                if (board.findMove(TKind.white, 4, move))
                    board.move(move, TKind.white);
            }
            System.out.print("Iteration#=" + i + " Result:" + board.getCounter(TKind.black) + "-" + board.getCounter(TKind.white));
            if (board.getCounter(TKind.black) > board.getCounter(TKind.white)) win++;
            if (board.getCounter(TKind.black) == board.getCounter(TKind.white)) drawn++;
            System.out.println("  Win#=" + win + "  Drawn#=" + drawn);
        }
        System.out.println("Total#=" + n + "  Win#=" + win + "  Drawn#=" + drawn);
    }

}
