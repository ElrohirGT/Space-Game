package packages.twoD; 
import java.util.Collection;
import java.util.List;

/**
 * The Vector class is used to store an arrow using containing an x and
 * a y distance. It is commonly used in physics or graphics math.
 * Unlikethe official Vector, this one is not a type of list and cannot
 * contain a variable number of dimensions. Also, it can only store two
 * doubles and no other types of objects. Therefore, it contains a number
 * of helpful methods to interact with it in a mathmatical way which is
 * not supported by the official Vector class.
 * 
 * @author RcCookie
 * @version 1.1
 */
public class Vector{

    /**
     * Used to store the vectors x distance.
     */
    private double x;
    
    /**
     * Used to store the vectors y distance.
     */
    private double y;

    /**
     * Creates a new zero vector.
     */
    public Vector(){
        x=y=0;
    }

    /**
     * Creates a new Vector from the given one.
     * The new Vector will be identical with the given one, but is a
     * different object.
     * 
     * @param v The Vector to create the new Vector from
     */
    public Vector(Vector v){
        x=v.getX();
        y=v.getY();
    }

    /**
     * Creates a new Vector with the given x and y distances.
     * 
     * @param x The x distance of the new Vector
     * @param y The y distance of the new Vector
     */
    public Vector(double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     * Creates a new Vector with the given angle and length in that
     * direction. If the length is negative, the vector will point in
     * the opposite direction.
     * The boolean parameter is irrelevant but neccessary to differ
     * this constructor from the one with an x and y distance. It does
     * not matter what is given here.
     * 
     * @param a The angle the new Vector should point at
     * @param l The length of the new Vector
     * @param b Identifier to this constructor. Value does not matter
     */
    public Vector(int a, double l, boolean b){
        x=l*Math.cos(Math.toRadians(a));
        y=l*Math.sin(Math.toRadians(a));
    }

    /**
     * Creates a position Vector to the given point, meaning it
     * connects the point of origin to the given point.
     * 
     * @param c The point to create the position vector from
     */
    public Vector(Point c){
        x=c.getX();
        y=c.getY();
    }


    /**
     * Returns the x distance of the Vector.
     * 
     * @return The x distance
     */
    public double getX(){
        return x;
    }

    /**
     * Returns the y distance of the Vector.
     * 
     * @return The y distance
     */
    public double getY(){
        return y;
    }

    /**
     * Returns the angle the Vector is pointing at.
     * 
     * @return The Vectors angle
     */
    public int getAngle(){
        if(x>=0){
            if(y>=0)return (int)Math.toDegrees(Math.atan(y/x));
            return 90 + (int)Math.toDegrees(Math.atan(y/x));
        }
        if(y<0)return 180 + (int)Math.toDegrees(Math.atan(y/x));
        return 270 + (int)Math.toDegrees(Math.atan(y/x));
    }

    /**
     * Returns the absolute of the Vector, meaning its length.
     * 
     * @return The length of the Vector
     */
    public double abs(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    /**
     * Return a new Point the Vector would lead to when starting in
     * the point of origin.
     * 
     * @return A new Point with the Vectors distance to the origin
     */
    public Point getPoint(){
        return new Point(this);
    }

    /**
     * Returns a new Vector with inverted values. This means that its
     * absolute will be identical with the input Vector and the angle
     * will differ to 180°. The Vector itself will stay unchanged.
     * 
     * @return The negative Vector
     */
    public Vector negative(){
        return new Vector(-x, -y);
    }

    /**
     * Inverts the Vectors direction while keeping its length. This
     * changes the Vector itself rather than creating a new one.
     * 
     * @return The Vector itself
     */
    public Vector invert(){
        return this.scale(-1);
    }

    /**
     * Adds the given distance onto the Vectors end with the same
     * angle as the Vector had before.
     * 
     * @param d the distance to add
     * @return The Vector ifself
     */
    public Vector add(double d){
        add(new Vector(getAngle(), d));
        return this;
    }

    /**
     * Adds the given Vector onto the Vector.
     * 
     * @param v The Vector to add
     * @return The Vector itself
     */
    public Vector add(Vector v){
        x+=v.getX();
        y+=v.getY();
        return this;
    }

    /**
     * Adds all given Vectors onto the Vector.
     * 
     * @param l The Vectors to add
     * @return The Vector itself
     */
    public Vector addAll(List<Vector> l){
        for (Vector v : l) add(v);
        return this;
    }

    /**
     * Performs a scalar multiplication with the given value on
     * this Vector. This means the Vector will be scaled to the
     * value as percentage.
     * s
     * @param s The scalar to multiply with
     * @return The Vector itself
     */
    public Vector scale(double s){
        x*=s;
        y*=s;
        return this;
    }
    
    /**
     * Divides the Vector through the given scalar.
     * @param s The scalar th divide through
     * @return The Vector itself
     */
    public Vector divide(double s){
        x/=s;
        y/=s;
        return this;
    }

    public String toString(){
        return "[" + x + "," + y + "]: " + super.toString();
    }


    /**
     * Returns a new Vector representing the sum of the two given
     * Vectors.
     * 
     * @param v The first Vector
     * @param w The second Vector
     * @return The sum of the Vectors
     */
    public static Vector add(Vector v, Vector w){
        return new Vector(v).add(w);
    }

    /**
     * Returns a new Vector representing the sum of all given
     * Vectors.
     * 
     * @param c The Vectors to add
     * @return The sum of the Vectors
     */
    public static Vector addAll(Collection<Vector> c){
        Vector v = new Vector();
        for(Vector w : c)v.add(w);
        return v;
    }

    /**
     * Returns the scalar product of the two given Vectors. This
     * means the sum of the product each of the x and y values.
     * The return value is a double representing the area that
     * the two vectors span.
     * 
     * @param v The first Vector
     * @param w The second Vector
     * @return The area the Vectors span
     */
    public static double sProduct(Vector v, Vector w){
        return v.getX() * w.getX() + v.getY() * w.getY();
    }

    /**
     * Returns the cross product of the two Vectors. This is a
     * new Vector.
     * 
     * @param v The first Vector
     * @param w The second Vector
     * @return The cross product of the two Vectors
     */
    public static Vector xProduct(Vector v, Vector w){
        return new Vector(v.getY() - w.getY(), v.getX() - w.getX());
    }

    /**
     * Returns a new Vector that represents the orthogonal
     * projection of the first Vector onto the second one.
     * If the result is zero, the first Vector is a zero
     * Vector or the two Vectors are orthogonal.
     * 
     * @param v The Vector th be projected
     * @param onto The Vector to be projected onto
     * @return The representation of the projection
     * @throws ArithmeticException If the Vector to project
     * onto is a zero Vector
     */
    
    public static Vector project(Vector v, Vector onto){
        return new Vector(onto).scale(sProduct(v, onto) / Math.pow(onto.abs(), 2));
    }
}