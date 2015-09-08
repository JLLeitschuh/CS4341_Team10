import java.util.Comparator;

public class NeighborComparator implements Comparator<Neighbor> {

    @Override
    public int compare(Neighbor p, Neighbor q){
        return p.getPriority() - q.getPriority();
    }
}
