import java.util.HashMap;
import java.util.Map;

public class AStarSearch2 {

    public static void runAStarSearch(SquareGrid graph) {
        FrontierQueue frontier = new FrontierQueue();
        frontier.add(new Neighbor(graph.getStart(), graph));
        Map<Neighbor, Integer> costSoFar = new HashMap<Neighbor, Integer>();

        int i = 0;
        while (!frontier.isEmpty()) {
            System.out.println("Act Number: " + i);
            i++;

            Neighbor current = frontier.remove();
            if (current.isPoint(graph.getGoal())){
                break;
            }


            //frontier.addAll(current.aStarSearch(costSoFar));
        }
    }
}
