import java.util.*;

public class AStarSearch {

    public static Neighbor runAStarSearch(SquareGrid graph, IHeuristic heuristic) {
        int numberOfNodesExpanded = 0; // The number of nodes expanded by A*
        int scoreOfPath = 0; // The score of the path A* used

        // Construct the initial neighbor
        Neighbor startNeighbor = new Neighbor(graph.getStart(), graph, false);
        SquareGrid initialCopy = new SquareGrid(graph);
        Neighbor startNeighborDemolish = new Neighbor(initialCopy.getStart(), graph, true);
        FrontierQueue frontier = new FrontierQueue();
        // The start node must be in the frontier.
        frontier.add(startNeighbor);
        frontier.add(startNeighborDemolish);
        Map<Neighbor, Integer> costSoFar = new HashMap<>();
        costSoFar.put(startNeighbor, startNeighbor.getPriority());
        costSoFar.put(startNeighborDemolish, startNeighborDemolish.getPriority());

        Neighbor current = null;
        while (!frontier.isEmpty()) {
            current = frontier.remove();
            System.out.println("Poping new: " + current);
            numberOfNodesExpanded++;
            if (current.isPoint(graph.getGoal())){
                System.out.println("Solved");
                break;
            }
            frontier.addAll(current.aStarSearch(costSoFar, heuristic));
        }
        // That is a lambda function.
        List<Neighbor> path = reconstructPath(current);//o -> System.out.println(o.getPoint()));
        System.out.println();
        System.out.println(" ---- results ----");
        int numberOfActions = 0;
        for(Neighbor n  : path){
            numberOfActions += n.getDirection().moveTotal;
        }
        System.out.println("Number of actions: " + numberOfActions);
        System.out.println("Series of actions: ");
        reconstructActions(current).forEach(a ->System.out.println(a));
        System.out.println();
        for(Neighbor n: path){
            System.out.println(n);
            System.out.println(n.getPriority());
            scoreOfPath += costSoFar.get(n);
        }
        System.out.println("\nScore of path: " + scoreOfPath);
        System.out.println("Number of nodes expanded: " + numberOfNodesExpanded);

        return current;
    }

    public static List<BaseAction> reconstructActions(Neighbor goalNeighbor){
        return goalNeighbor.getActions();
    }

    public static List<Neighbor> reconstructPath(Neighbor goalNeighbor){
        // Gets the path from the goal
        return goalNeighbor.getPath();
    }
}
