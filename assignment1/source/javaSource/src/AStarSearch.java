import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class AStarSearch {

    public static FrontierQueue runAStarSearch(SquareGrid graph) {
        // Construct the initial neighbor
        Neighbor startNeighbor = new Neighbor(graph.getStart(), graph);
        FrontierQueue frontier = new FrontierQueue();
        // The start node must be in the frontier.
        frontier.add(startNeighbor);
        Map<Neighbor, Integer> costSoFar = new HashMap<>();
        costSoFar.put(startNeighbor, graph.getStart().getCost());

        Neighbor current = null;
        while (!frontier.isEmpty()) {
            current = frontier.remove();
            System.out.println("Poping new");

            if (current.isPoint(graph.getGoal())){
                System.out.println("Solved");
                break;
            }
            frontier.addAll(current.aStarSearch(costSoFar));
        }

        // That is a lambda function.
        reconstructPath(current).forEach(o -> System.out.println(o.getPoint()));

        return frontier;
    }

    public static List<Neighbor> reconstructPath(Neighbor goalNeighbor){
        // Gets the path from the goal
        return goalNeighbor.getPath();
    }
}
