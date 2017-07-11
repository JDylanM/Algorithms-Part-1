import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

public class FastCollinearPoints {
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument is null");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Array contains null");
            }

            if (i != 0) {
                Point prev = points[i-1];
                Point current = points[i];
                if (prev.compareTo(current) == 0) {
                    throw new IllegalArgumentException("Array contains duplicates");
                }
            }
        }

        HashSet<Point> addedSegments = new HashSet<>();
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point[] sortedArray = Arrays.copyOf(points, points.length);
            ArrayList<Point> adjacentPoints = new ArrayList<>();
            Point min = p;
            Point max = p;

            adjacentPoints.add(p);
            Arrays.sort(sortedArray, p.slopeOrder());

            for (int j = 0; j < points.length; j++) {
                Point currentPoint = sortedArray[j];

                if (currentPoint == p) continue;
                if (adjacentPoints.size() == 1) {
                    adjacentPoints.add(currentPoint);
                    min = min(min, currentPoint);
                    max = max(max, currentPoint);
                }
                else if (collinear(adjacentPoints, currentPoint)) {
                    adjacentPoints.add(currentPoint);
                    min = min(min, currentPoint);
                    max = max(max, currentPoint);
                }
                else {
                    if (adjacentPoints.size() > 3) {
                        if (noDuplicate(min, addedSegments)) {
                            addedSegments.add(min);
                            foundSegments.add(new LineSegment(min, max));
                        }
                    }
                    adjacentPoints.subList(1, adjacentPoints.size()).clear();
                    min = p;
                    max = p;
                }

            }
        }
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    private boolean noDuplicate(Point min, HashSet<Point> addedSegments) {
        if (addedSegments.contains(min)) {
            return false;
        }
        return true;
    }

    private Point min(Point min, Point currentPoint) {
        if (currentPoint.compareTo(min) < 0) return currentPoint;
        return min;
    }

    private Point max(Point max, Point currentPoint) {
        if (currentPoint.compareTo(max) > 0) return currentPoint;
        return max;
    }

    private boolean collinear(ArrayList<Point> points, Point currentPoint) {
        Point p = points.get(0);
        Point q = points.get(1);

        if (p.slopeTo(q) == p.slopeTo(currentPoint)) {
            return true;
        }
        return false;
    }

    private void printSortedArray(Point[] points) {
        for (Point p: points) {
            StdOut.printf("(%d, %d)", p.x, p.y);
            StdOut.println("");
        }
        StdOut.println("END");
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
