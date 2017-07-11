import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private LineSegment[] segments;


    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument is null");
        }

        Point[] sortedArray = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedArray);

        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i] == null) {
                throw new IllegalArgumentException("Array contains null");
            }


            if (i != 0) {
                Point prev = sortedArray[i-1];
                Point current = sortedArray[i];
                if (prev.compareTo(current) == 0) {
                    throw new IllegalArgumentException("Array contains duplicates");
                }
            }
        }

        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p+1; q < points.length - 2; q++) {
                for (int r = q+1; r < points.length - 1; r++) {
                    for (int s = r+1; s < points.length; s++) {
                        if (sortedArray[p].slopeTo(sortedArray[q]) == sortedArray[p].slopeTo(sortedArray[r]) &&
                            sortedArray[p].slopeTo(sortedArray[r]) == sortedArray[p].slopeTo(sortedArray[s])) {
                            foundSegments.add(new LineSegment(sortedArray[p], sortedArray[s]));
                        }
                    }
                }
            }
        }

        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
