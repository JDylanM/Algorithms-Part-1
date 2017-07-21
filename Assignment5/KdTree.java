import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.LinkedList;
import java.util.Queue  ;
import edu.princeton.cs.algs4.SET;

public class KdTree {
    private int size = 0;
    public Node root;             // root of BST

    private static class Node {
        private Point2D point;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private boolean vertical;

        public Node(Point2D point, RectHV rect, Node lb, Node rt, boolean vertical) {
            this.point = point;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.vertical = vertical;
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

        // handle first insert
        if (size == 0) {
            root = new Node(p, new RectHV(0, 0, 1, 1), null, null, true);
            size++;
        }
        else {
            root = insert(root, null, p, true, false);
        }
    }

    private Node insert(Node node, Node parentNode, Point2D insertingPoint, boolean vertical, boolean higher) {
        if (node == null) {
            size++;
            RectHV nodeRect = null;
            RectHV pRect = parentNode.rect;
            Point2D pPoint = parentNode.point;
            if(!vertical && !higher) nodeRect = new RectHV(pRect.xmin(), pRect.ymin(), pPoint.x(), pRect.ymax());
            if(!vertical && higher) nodeRect = new RectHV(pPoint.x(), pRect.ymin(), pRect.xmax(), pRect.ymax());
            if(vertical && !higher) nodeRect = new RectHV(pRect.xmin(), pRect.ymin(), pRect.xmax(), pPoint.y());
            if(vertical && higher) nodeRect = new RectHV(pRect.xmin(), pPoint.y(), pRect.xmax(), pRect.ymax());
            return new Node(insertingPoint, nodeRect, null, null, vertical);
        }

        if (vertical) {
            double cmp = insertingPoint.x() - node.point.x();
            if (cmp < 0) node.lb = insert(node.lb, node, insertingPoint, false, false);
            else node.rt = insert(node.rt, node, insertingPoint, false, true);
        }
        else {
            int cmp = insertingPoint.compareTo(node.point);
            if (cmp < 0) node.lb  = insert(node.lb, node, insertingPoint, true, false);
            else node.rt = insert(node.rt, node, insertingPoint, true, true);
        }

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
        for (Node n: nodes()) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            n.point.draw();
            StdDraw.setPenRadius();
            if (n.vertical) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(n.point.x(), n.rect.ymin(), n.point.x(), n.rect.ymax());
            }
            else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(n.rect.xmin(), n.point.y(), n.rect.xmax(), n.point.y());
            }
            StdOut.println(n.vertical);
        }
    }

    public Iterable<Node> nodes() {
        LinkedList<Node> list = new LinkedList<>();
        nodes(root, list);
        return list;
    }

    private void nodes(Node x, LinkedList<Node> list) {
        if (x == null) return;
        list.add(x);
        nodes(x.lb, list);
        nodes(x.rt, list);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("argument is null");
        }

        LinkedList<Point2D> list = new LinkedList<>();
        points(root, list, rect);
        return list;
    }

    private void points(Node x, LinkedList<Point2D> list, RectHV rangeRect) {
        if (x == null) return;
        if (x.rect.intersects(rangeRect))
        {
            if(rangeRect.contains(x.point)) list.add(x.point);
            points(x.lb, list, rangeRect);
            points(x.rt, list, rangeRect);
        }
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
        Point2D point = new Point2D(0.2,0.5);
        Point2D point2 = new Point2D(0.3,0.2);
        Point2D point3 = new Point2D(0.1,0.2);
        Point2D point4 = new Point2D(0.5,0.1);
        Point2D point5 = new Point2D(0.3,0.9);
        KdTree tree = new KdTree();
        tree.insert(point);
        tree.insert(point2);
        tree.insert(point3);
        tree.insert(point4);
        tree.insert(point5);
        for(Node p:tree.nodes()) {
            StdOut.println(p.point.x());
        }
        StdOut.println(tree.contains(point2));
    }
}
