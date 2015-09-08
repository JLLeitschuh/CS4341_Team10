import java.util.HashMap;
import java.util.Map;

public class AStarSearch {

    public static void runAStarSearch(SquareGrid graph) {
        Neighbor startNeighbor = new Neighbor(graph.getStart(), graph);
        FrontierQueue frontier = new FrontierQueue();
        frontier.add(startNeighbor);
        Map<Neighbor, Integer> costSoFar = new HashMap<Neighbor, Integer>();
        costSoFar.put(startNeighbor, 1);

        while (!frontier.isEmpty()) {
            Neighbor current = frontier.remove();
            System.out.println("Poping new");
            if (current.isPoint(graph.getGoal())){
                break;
            }
            frontier.addAll(current.aStarSearch(costSoFar));
        }
    }
}
