import java.util.*;

public class AStarSearch {

    public static List runAStarSearch(SquareGrid graph) {
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
        List<Neighbor> path = reconstructPath(current);//o -> System.out.println(o.getPoint()));
        System.out.println();
        System.out.println(" ---- results ----");
        System.out.println("Number of actions: " + path.size());
        System.out.print("Series of actions: ");
        for(Neighbor n : path) {
            System.out.print(n.getDirection().toString() + ", ");
            scoreOfPath += n.getPriority();
        }
        System.out.println();
        for(Neighbor n: path){
            System.out.println(n);
            System.out.println(n.getPriority());
        }
        System.out.println("\nScore of path: " + scoreOfPath);
        System.out.println("Number of nodes expanded: " + numberOfNodesExpanded);

        return path;
    }

    public static List<Neighbor> reconstructPath(Neighbor goalNeighbor){
        // Gets the path from the goal
        return goalNeighbor.getPath();
    }
}
