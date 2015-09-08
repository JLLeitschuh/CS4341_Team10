import java.util.HashMap;
import java.util.Map;

public class AStarSearch {

    public static void runAStarSearch(SquareGrid graph) {
        FrontierQueue frontier = new FrontierQueue();
        frontier.add(new Neighbor(graph.getStart(), graph));
        Map<Neighbor, Integer> costSoFar = new HashMap<Neighbor, Integer>();

        while (!frontier.isEmpty()) {
            Neighbor current = frontier.remove();
            if (current.isPoint(graph.getGoal())){
                break;
            }
            frontier.addAll(current.aStarSearch(costSoFar));
        }
    }
}
