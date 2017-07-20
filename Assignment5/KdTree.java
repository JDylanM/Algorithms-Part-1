import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import edu.princeton.cs.algs4.SET;

public class KdTree {
    private int size = 0;
    private Node root;             // root of BST

    private static class Node {
        private Point2D point;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D point, RectHV rect, Node lb, Node rt) {
            this.point = point;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
        }
    }


    public KdTree() {
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    // add if point already exist then return;
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("called insert() with a null key");
        if (contains(p)) return;
        root = insert(root, p, true);
    }

    private Node insert(Node node, Point2D insertingPoint, boolean vertical) {
        if (node == null) {
            return new Node(insertingPoint, null, null, null);
        }

        if (vertical) {
            double cmp = insertingPoint.x() - node.point.x();
            if (cmp < 0) node.lb = insert(node.lb, insertingPoint, false);
            else node.rt = insert(node.rt, insertingPoint, false);
        }
        else {
            int cmp = insertingPoint.compareTo(node.point);
            if (cmp < 0) node.lb  = insert(node.lb,  insertingPoint, true);
            else node.rt = insert(node.rt, insertingPoint, true);
        }
        size++;
        return node;
    }

    public boolean contains(Point2D point) {
        if (point == null) throw new IllegalArgumentException("argument to contains() is null");
        return contains(root, point, true);
    }

    private boolean contains(Node node, Point2D point, boolean vertical) {
        if (point == null) throw new IllegalArgumentException("called contains with a null point");
        if (node == null) return false;
        //StdOut.printf("node point %f %f vs searching point %f %f \n", node.point.x(), node.point.y(), point.x(), point.y());
        if (node.point.equals(point)) return true;
        if (vertical) {
            double cmp = point.x() - node.point.x();
            if (cmp < 0) return contains(node.lb, point, false);
            else return contains(node.rt, point, false);
        }
        else if (!vertical) {
            int cmp = point.compareTo(node.point);
            if (cmp < 0) return contains(node.lb,  point, true);
            else return contains(node.rt, point, true);
        }

        return true; // should never come here

    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("argument is null");
        }

        ArrayList<Point2D> points = new ArrayList<>();

        return points;
    }

/*
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
    */

    public static void main(String[] args) {
        Point2D point = new Point2D(1,2);
        Point2D point2 = new Point2D(3,4);
        Point2D point3 = new Point2D(0,1);
        Point2D point4 = new Point2D(2,2);
        Point2D point5 = new Point2D(1,2);
        KdTree tree = new KdTree();
        tree.insert(point);
        tree.insert(point2);
        tree.insert(point4);
        StdOut.println(tree.contains(point3));
    }
}
