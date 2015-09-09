import java.util.*;

public class AStarSearch {

    public static ArrayList runAStarSearch(SquareGrid graph) {

        ArrayList<Neighbor> neighborPath = new ArrayList(); // The list of neighbors A* visited
        int numberOfNodesExpanded = 0; // The number of nodes expanded by A*
        int scoreOfPath = 0; // The score of the path A* used


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
            numberOfNodesExpanded++;

            if (current.isPoint(graph.getGoal())){
                System.out.println("Solved");
                break;
            }
            frontier.addAll(current.aStarSearch(costSoFar));
        }

        // That is a lambda function.
        reconstructPath(current).forEach(o -> neighborPath.add(o));//o -> System.out.println(o.getPoint()));
        System.out.println();
        System.out.println(" ---- results ----");
        System.out.println("Number of nodes expanded: " + numberOfNodesExpanded);
        Iterator<Neighbor> iter = neighborPath.iterator();
        while (iter.hasNext()) {
            Neighbor n = iter.next();
            scoreOfPath += n.getPoint().getCost();
        }
        System.out.println("Score of path: " + scoreOfPath);
        return neighborPath;
    }

    public static List<Neighbor> reconstructPath(Neighbor goalNeighbor){
        // Gets the path from the goal
        return goalNeighbor.getPath();
    }
}
