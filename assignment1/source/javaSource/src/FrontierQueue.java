import java.util.PriorityQueue;


public class FrontierQueue extends PriorityQueue<Neighbor> {
    public FrontierQueue(){
        super(new NeighborComparator());
    }

}
