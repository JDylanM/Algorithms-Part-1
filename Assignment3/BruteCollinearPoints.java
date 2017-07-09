import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
//Add

public class BruteCollinearPoints {
    private LineSegment[] privateSegments;

    public int numberOfSegments() {
        return 0;
    }

    public BruteCollinearPoints(Point[] points) {
        privateSegments = new LineSegment[points.length];
        Arrays.sort(points);

        for(int p = 0; p < points.length; p++) {
            for(int q = p+1; q < points.length; q++) {
                for(int r = q+1; r < points.length; r++) {
                    for(int s = r+1; s < points.length; s++) {
                        if(points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
                            points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                                //https://stackoverflow.com/questions/2843366/how-to-add-new-elements-to-an-array
                            }
                    }
                }
            }
        }
    }
    public LineSegment[] segments() {
        return privateSegments;
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
            System.out.println(p.x);
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        //for (LineSegment segment : collinear.segments()) {
        //    StdOut.println(segment);
        //    segment.draw();
        //}
        StdDraw.show();
    }
}
