import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    private SET<Point2D> set;
    public PointSET() {
        set = new SET<>();
    }
    public boolean isEmpty() {
        return set.isEmpty();
    }
    public int size() {
        return set.size();
    }
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null");
        }

        set.add(p);
    }
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null");
        }

        return set.contains(p);
    }
    public void draw() {
        for(Point2D p: set) {
            p.draw();
        }
    }
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("argument is null");
        }

        ArrayList<Point2D> points = new ArrayList<>();

        for(Point2D p: set) {
            if (rect.contains(p)) {
                points.add(p);
            }
        }

        return points;
    }

    public Point2D nearest(Point2D p) {
        double minDistance = p.distanceTo(set.max());
        Point2D minPoint = set.max();
        for (Point2D pointInSet: set) {
            double distanceToPoint = p.distanceTo(pointInSet);
            if  (minDistance > distanceToPoint ) {
                minDistance = distanceToPoint;
                minPoint = pointInSet;
            }
        }
        return minPoint;
    }

    public static void main(String[] args) {

    }
}
