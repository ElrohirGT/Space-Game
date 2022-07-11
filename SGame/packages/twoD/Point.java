package packages.twoD;
import java.util.List;
public class Point{
    public double X, Y;
    public Point(){
        X=0;
        Y=0;
    }
    public Point(Point p){
        X=p.X;
        Y=p.Y;
    }
    public Point(double x, double y){
        X=x;
        Y=y;
    }
    public Point(int x, int y){
        X=(double)x;
        Y=(double)y;
    }
    public Point(Vector v){
        X=v.getX();
        Y=v.getY();
    }
    public Point(Point a, Point b){
        X=(a.getX()+b.getX())/2;
        Y=(a.getY()+b.getY())/2;
    }
    public Point(List<Point> l){
        for (Point c : l) {
            X+=c.getX();
            Y+=c.getY();
        }
        X/=l.size();
        Y/=l.size();
    }
    public double getX(){
        return X;
    }
    public double getY(){
        return Y;
    }
    public Vector toVector(){
        return new Vector(this);
    }
    public double abs(){
        return new Vector(this).abs();
    }
    public Point getNegative(){
        return new Point(-X, -Y);
    }
    public Point add(Point c){
        X+=c.getX();
        Y+=c.getY();
        return this;
    }
    public Point add(List<Point> c){
        for (Point co : c) {
            add(co);
        }
        return this;
    }
    public Point addX(double d){
        X+=d;
        return this;
    }
    public Point addY(double d){
        Y+=d;
        return this;
    }
    public String toString(){
        return "(" + X + "," + Y + "): " + super.toString();
    }
    public static final Point average(Point a, Point b){
        return new Point(a,b);
    }
    public static final Point average(List<Point> l){
        double a = 0, b = 0;
        for (Point c : l) {
            a+=c.getX();
            b+=c.getY();
        }
        return new Point(a,b);
    }
}