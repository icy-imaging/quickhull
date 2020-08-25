package plugins.adufour.quickhull;

import icy.plugin.abstract_.Plugin;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class QuickHull2D extends Plugin
{
    public static List<Point2D> computeConvexEnvelope(List<Point2D> points)
    {
        ArrayList<Point2D> envelope = new ArrayList<Point2D>();
        
        // find two points: right (bottom) and left (top)
        
        Point2D l = points.get(0);
        Point2D r = points.get(0);
        
        for (int i = 1; i < points.size(); i++)
        {
            Point2D p = points.get(i);
            if (r.getX() > p.getX() || (r.getX() == p.getX() && r.getY() > p.getY())) r = p;
            if (l.getX() < p.getX() || (l.getX() == p.getX() && l.getY() < p.getY())) l = p;
        }
        
        // create neighbor lists
        
        ArrayList<Point2D> neighbors1 = new ArrayList<Point2D>();
        ArrayList<Point2D> neighbors2 = new ArrayList<Point2D>();
        
        for (Point2D p : points)
        {
            if (p == l || p == r) continue;
            
            int upper = Line2D.relativeCCW(r.getX(), r.getY(), l.getX(), l.getY(), p.getX(), p.getY());
            
            if (upper > 0)
            {
                neighbors1.add(p);
            }
            else if (upper < 0)
            {
                neighbors2.add(p);
            }
        }
        
        envelope.add(r);
        
        quickhull(envelope, r, l, neighbors1);
        
        envelope.add(l);
        
        quickhull(envelope, l, r, neighbors2);
        
        return envelope;
    }
    
    private static void quickhull(ArrayList<Point2D> envelope, Point2D a, Point2D b, ArrayList<Point2D> neighbors)
    {
        if (neighbors.size() == 0) return;
        
        Point2D c = farthestpoint(a, b, neighbors);
        
        ArrayList<Point2D> al1 = new ArrayList<Point2D>();
        ArrayList<Point2D> al2 = new ArrayList<Point2D>();
        
        for (Point2D pt : neighbors)
        {
            if (pt == a || pt == b) continue;
            
            if (Line2D.relativeCCW(a.getX(), a.getY(), c.getX(), c.getY(), pt.getX(), pt.getY()) > 0)
            {
                al1.add(pt);
            }
            else if (Line2D.relativeCCW(c.getX(), c.getY(), b.getX(), b.getY(), pt.getX(), pt.getY()) > 0)
            {
                al2.add(pt);
            }
        }
        
        quickhull(envelope, a, c, al1);
        
        envelope.add(c);
        
        quickhull(envelope, c, b, al2);
    }
    
    private static Point2D farthestpoint(Point2D a, Point2D b, ArrayList<Point2D> points)
    {
        double maxD = -1;
        Point2D maxP = null;
        
        for (Point2D p : points)
        {
            if (p == a || p == b) continue;
            
            double dist = Line2D.ptLineDistSq(a.getX(), a.getY(), b.getX(), b.getY(), p.getX(), p.getY());
            
            if (dist > maxD)
            {
                maxD = dist;
                maxP = p;
            }
        }
        
        return maxP;
    }
}
