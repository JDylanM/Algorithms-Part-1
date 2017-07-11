import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.ArrayList;
//Add

public class FastCollinearPoints {
    private LineSegment[] segments;

    public int numberOfSegments() {
        return segments.length;
    }

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument is null");
        }

        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        for(int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point[] sortedArray = Arrays.copyOf(points, points.length);
            StdOut.printf("point p is %d, %d", p.x, p.y);
            StdOut.println("");
            Arrays.sort(sortedArray, p.slopeOrder());
            printSortedArray(sortedArray);
            ArrayList<Point> adjacentPoints = new ArrayList<>();
            adjacentPoints.add(p);
            for(int j = 0; j < points.length; j++) {
                // reset every third
                if(adjacentPoints.size() == 4) {
                     if(collinear(adjacentPoints)) {
                         foundSegments.add(new LineSegment(p, adjacentPoints.get(3)));
                     }
                     adjacentPoints.subList(1, 4).clear();
                 }
                adjacentPoints.add(sortedArray[j]);
            }

        }

        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);



    }

    private void printSortedArray(Point[] points) {
        for(Point p: points) {
            StdOut.printf("(%d, %d)", p.x, p.y);
            StdOut.println("");
        }
        StdOut.println("END");
    }

    private boolean collinear(ArrayList<Point> points) {
        Point p = points.get(0);
        for(int i = 1; i < points.size(); i++) {
            if(points.get(i) == p) {
                StdOut.println("true");
                return false;
            }
        }

        if(p.slopeTo(points.get(1)) == p.slopeTo(points.get(2)) &&
            p.slopeTo(points.get(2)) == p.slopeTo(points.get(3))) {
                return true;
            }
        return false;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
