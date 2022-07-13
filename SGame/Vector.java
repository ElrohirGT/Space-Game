/**
 * Write a description of class Vector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector  
{
    // instance variables - replace the example below with your own
    private double x;
    private double y;

    public Vector()
    {
        this(0,0);
    }
    
    /**
     * Constructor for objects of class Vector
     */
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double newX) { x = newX; }
    public void setY(double newY) { y = newY; }

    public Vector plus(Vector other)
    {
        return new Vector(x + other.getX(), y + other.getY());
    }
}