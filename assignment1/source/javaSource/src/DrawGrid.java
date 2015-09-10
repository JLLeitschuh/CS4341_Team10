import java.util.Iterator;
import java.util.List;

public class DrawGrid {

    public DrawGrid(SquareGrid grid , List<Neighbor> neighborPath) {
        System.out.println();
        System.out.println("---- draw grid ----");
        for (int y =0; y < grid.getHeight(); y++) {
            System.out.print("|");
            for (int x =0; x < grid.getWidth(); x++) {

                boolean neighborPathContainsPoint = false;
                Iterator<Neighbor> iter = neighborPath.iterator();
                for (Neighbor current : neighborPath) {
                    if (current.getPoint().isPoint(x, y)) {
                        System.out.print(current.getDirection().toString());
                        neighborPathContainsPoint = true;
                    }
                }
                if (!neighborPathContainsPoint) {
                    System.out.print(draw_tile(grid, x, y));
                }


                System.out.print("|");
            }
            System.out.println();
            System.out.print("-");
            for (int x =0; x < grid.getWidth(); x++) {
                System.out.print("--");
            }
            System.out.println();
        }

    }


    public String draw_tile(SquareGrid grid, int x, int y) {
        Point selectedPoint = grid.getPoint(x, y);
        int loc = selectedPoint.getCost();
        if (selectedPoint.isGoal()) {
            return "G";
        }
        else if (selectedPoint.isStart()) {
            return "S";
        }
        /*
        r = u "."
        if u 'number' in style and id in style[ u 'number']:r = u "%d" % style[u 'number'][id]
        if u 'point_to' in style and style[ u 'point_to'].get(id, None) is not None:
        (x1, y1)=id
                (x2, y2) = style[u 'point_to'][id]
        if x2 == x1 + 1:r = u "\u2192"
        if x2 == x1 - 1:r = u "\u2190"
        if y2 == y1 + 1:r = u "\u2193"
        if y2 == y1 - 1:r = u "\u2191"
        if u 'start' in style and id==style[u 'start']:r = u "A"
        if u 'goal' in style and id==style[u 'goal']:r = u "Z"
        if u 'path' in style and id in style[ u 'path']:r = u "@"
        if id in graph.walls:r = u "#" * width
        return r
        */
        return Integer.toString(loc);
    }
}
