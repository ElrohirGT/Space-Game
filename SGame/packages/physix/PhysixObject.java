package packages.physix;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import packages.twoD.*;

/**
 * The core of the physix game engine. It represents ao object with a mass and
 * a velocity. The physix class runs the physics of the movement as well as the
 * collision physics between physixObjecs and StaticObjects. It is abstract
 * ifself and is meant to be extended.
 * 
 * Collision physics are still WIP.
 * 
 * @author RcCookie
 * 
 * @author Poul Henriksen (Smooth moving)
 * @author Michael Kolling (Smooth moving)
 * @author Neil Brown (Smooth moving)
 * 
 * @version 1.0
 */
public abstract class PhysixObject extends Actor
{
    /**
     * This Vector represents the speed in this calculation frame. It determines
     * how far the object will move at the end of the frame. While calculations
     * it might not be very representive for the actual movement speed. For that
     * reason it is private.
     */
    private Vector speed;

    /**
     * This Vector represents the speed moved at the end of the last frame. It is
     * representive for the actual movement of the object and therefore can be
     * accessed by exdending classes directly. Changing this does not affect any
     * physics.
     */
    protected Vector velocity;

    /**
     * This Point represents the exact location of the object. This means it saves
     * the real location of the object in double coordinates.
     */
    private Point exact;
    

    //physix object values

    /**
     * This represents the mass of the object. It determines the matter of forces
     * onto the object and its behavior in collisions.
     */
    private double mass;

    /**
     * Represents the effekt of friction onto the object.
     */
    private double friction;

    /**
     * Detemines weather the object should be affected by gravity or not, meaning
     * weather it should fall to the bottom of the screen itself or not.
     */
    private boolean useGravity;

    /**
     * Determines weather the object should have wind resistance.
     */
    private boolean useWind;
    

    //physix world values

    /**
     * The acceleration on objects caused by gravity.
     */
    private static double gravity = -0.15;

    /**
     * The cw-value of the object, representing the objects wind resistance.
     */
    private double cw = 0.2;


    /**
     * Creates a new PhysixObject with the given weight.
     * The object will have the following default values: friction = 0.1,
     * useGravity = false, useWind = true.
     * 
     * @param weight The objects weight
     */
    public PhysixObject(double weight){
        setMass(weight);
        setFriction(0.1);
        useGravity(false);
        useWind(false);
        speed = new Vector();
        velocity = new Vector();
    }

    /**
     * Creates a new PhysixObject with the given weight and friction and sets
     * gravity and wind to the given booleans.
     * 
     * @param weight The objects weight
     * @param surfaceFriction The objects friction
     * @param usingGravity Weather the object should be affected by gravity
     * @param usingWind Weather the object should be affected by wind resistance
     */
    public PhysixObject(double weight, double surfaceFriction, boolean usingGravity, boolean usingWind){
        setMass(weight);
        setFriction(surfaceFriction);
        useGravity(usingGravity);
        useWind(usingWind);
        speed = new Vector();
        velocity = new Vector();
    }
    
    /**
     * First runs the run method which represents the action code in the
     * extending classes. Then it runs gravity, wind and friction resistance, 
     * moves the object to its new location using colissions and updates the
     * velocity Vector.
     * This method is final because overriding it would remove any physics
     * calculations. The run method is meant to be overridden as the act method
     * normaly.
     * 
     * @see run()
     */
    public final void act(){
        run();
        
        if(useGravity)addAcc(new Vector(0, -gravity));
        if(useWind)addForce(new Vector(speed.getAngle(), -cw*Math.pow(speed.abs(), 2)*0.1, true));
        addForce(new Vector(speed.getAngle(), friction*speed.abs()*-0.1, true));
        collidMove();
        velocity = new Vector(speed);
    }

    /**
     * This method should contain all stuff that usually would be in the act method.
     * It is called once per frame by the act method. This method has to be
     * overridden in the extending classes, even if it stays empty. Usually though
     * it contains for examle user input actions.
     * This method is abstract mainly to be noticed by the user as he is used
     * to overriding the act method which is not possible.
     */
    public abstract void run();

    /**
     * This method moves the object by the distance of the speed Vector while checking
     * collisions. This method is not meant to be used by the user to move the object.
     */
    private void collidMove(){
        /*if(clearTo(getLocation().add(speed.getPoint()))){
            move(speed);
            return;
        }*/
        Point fin = new Point(speed).add(getLocation());
        for(int i=0; i<speed.abs(); i++){
            move(new Vector(speed.getAngle(), 1, true));
            if(getOneIntersectingObject(PhysixObject.class)!=null){
                PhysixObject o = (PhysixObject)getOneIntersectingObject(PhysixObject.class);
                Vector force = getForce().add(o.getForce().negative());
                move(new Vector(speed.getAngle(), 2, true).negative());
                speed = new Vector(speed.getAngle(), i, true);
                addForce(new Vector(force).scale(mass/(mass+o.getMass())));
                o.addForce(new Vector(force).scale(o.getMass()/(mass+o.getMass())));
                return;
            }
            else if(getOneIntersectingObject(StaticObject.class)!=null){
                move(new Vector(speed.getAngle(), -2, true));
                speed = new Vector(speed.getAngle(), 0, true);
                return;
            }
        }
        setLocation(fin);
    }
    
    //physix input

    /**
     * Adds acceleration to the object. The acceleration is determined by the force
     * input and the objects weight.
     * 
     * @param force The force Vector to add
     */
    public void addForce(Vector force){
        addAcc(force.divide(mass));
    }

    /**
     * Adds acceleration to the object. The acceleration is not determined by the
     * weight of the object. For that reason this method should be used carefully
     * and mainly finds use in gravity physics as mass does not play a role here.
     * Usually though addForce should be used.
     * 
     * @param acceleration The acceleration Vector to add
     */
    public void addAcc(Vector acceleration){
        speed.add(acceleration);
    }

    /**
     * Sets the speed of the object to the given Vector. Should mainly be used to
     * stop rather than to move objects.
     * 
     * @param speed The new speed
     */
    public void setSpeed(Vector speed){
        this.speed = speed;
    }
    
    //physix setup

    /**
     * Gives the object a new mass. The mass will be corrected if neccessary to
     * be greater than 0 to prevent any glitches.
     * Warning: If the mass is less than 1, forces very quickly become very
     * powerful.
     * 
     * @param m The new mass
     */
    public void setMass(double m){
        mass = m;
        if(mass<=0)mass = 0.1;
    }

    /**
     * Sets the friction the object has on the ground. It will be corrected if
     * neccessary to fit the allowed range between 0 and 1. 0 means no friction.
     * 
     * @param f The new friction
     */
    public void setFriction(double f){
        friction = f;
        if(friction<0)friction = 0;
        else if(friction>1)friction = 1;
    }

    /**
     * Sets the gravity to be active or not active for this object.
     * 
     * @param b Weather gravity is active or not
     */
    public void useGravity(boolean b){
        useGravity = b;
    }

    /**
     * Adjusts the gravity to a new acceleration value. If gravity is greater
     * than 0, objects will be faling upwards. The gravity value will be used
     * for all objects that use gravity.
     * 
     * @param g The new gravity acceleration
     */
    public static void setGravity(double g){
        gravity = g;
    }

    /**
     * Sets the wind resistance to be active or not active for this object.
     * 
     * @param b Weather wind resistance is active or not
     */
    public void useWind(boolean b){
        useWind = b;
    }

    /**
     * Adjusts the cw-value of this object. It will be corrected if neccessary
     * to be greater or equal to 0.
     * 
     * @param cw the new cw value
     */
    public void setCw(double cw){
        this.cw = cw;
        if(this.cw<0)this.cw=0;
    }

    /**
     * Checks weather thepath is clear to the given Point.
     * 
     * @param p The Point to check the way to
     * @return If there are any PhysixObjects or StaticObjects in the way
     */
    private boolean clearTo(Point p){
        Sensor s = new Sensor(this);
        getWorld().addObject(s, 0, 0);
        java.util.List<Actor> c = s.collTo(p);
        getWorld().removeObject(s);
        if(c.size()>1)return false;
        return true;
    }

    /**
     * Checks weather the object is moving forwards compared to its rotation.
     * Can be helpful for braking mechanisms.
     */
    public boolean forwards(){
        if(speed.abs()==0)return true;
        return Vector.project(new Vector(getRotation(), 1, true), velocity).getAngle()==velocity.getAngle();
    }
    

    //Smooth moving

    /**
     * Moves the object by the given distance.
     * 
     * @param distance The distance to move
     */
    public void move(int distance){
        move((double)distance);
    }

    /**
     * Moves the object by the given exact distance.
     * 
     * @param distance The exact distance to move
     */
    public void move(double distance){
        setLocation(new Point(exact.X + Math.cos(Math.toRadians(getRotation())) * distance,
        exact.Y + Math.sin(Math.toRadians(getRotation())) * distance));
    }

    /**
     * Moves the object by the given Vector.
     * 
     * @param v The Vector to move
     */
    public void move(Vector v){
        setLocation(new Point(exact.X + v.getX(), exact.Y + v.getY()));
    }

    /**
     * Sets the object to a new location.
     * 
     * @param x The new x coordinate
     * @param y The new y coordinate
     */
    public void setLocation(double x, double y){
        setLocation(new Point(x, y));
    }

    /**
     * Sets the object to a new exact location.
     * 
     * @param x The new exact x coordinate
     * @param y The new exact y coordinate
     */
    public void setLocation(int x, int y){
        setLocation(new Point(x, y));
    }

    /**
     * Sets the object to a new location given through a point.
     * 
     * @param location The location to set
     */
    public void setLocation(Point location){
        exact = location;
        super.setLocation((int)(location.X+0.5), (int)(location.Y+0.5));
    }
    

    //get methods

    /**
     * Returns the location of the object
     * 
     * @return The objects location
     */
    public Point getLocation(){
        return exact;
    }

    /**
     * Returns the x coordinate of the object
     * 
     * @return The obejcts x coordinate
     */
    public int getX(){
        return (int)exact.getX();
    }

    /**
     * Returns the y coordinate of the object
     * 
     * @return The obejcts y coordinate
     */
    public int getY(){
        return (int)exact.getY();
    }

    /**
     * Returns the velocity Vector, meaning the last frames movement.
     * 
     * @return The velocity Vector
     */
    public Vector getVelocity(){
        return velocity;
    }

    /**
     * Returns the absolute movement speed in the last frame.
     * 
     * @return The obejcts movement speed
     */
    public double getSpeed(){
        return velocity.abs();
    }

    /**
     * Return the force that this object carries at the moment.
     * 
     * @return The objects force
     */
    public Vector getForce(){
        return new Vector(speed).scale(mass);
    }

    /**
     * Returns the objects mass.
     * 
     * @return The objects mass
     */
    public double getMass(){
        return mass;
    }
    

    /**
     * This class is used to check collisions in a certain area.
     * It is still WIP.
     */
    private class Sensor extends Actor{
        PhysixObject o;
        int h;
        protected Sensor(PhysixObject object){
            o = object;
            h = o.getImage().getHeight();
        }
        protected Sensor(PhysixObject object, int height){
            o = object;
            h = height;
        }
        protected boolean clearTo(java.lang.Class<?> collider, Point p){
            if(collider==null)return true;
            Vector path = new Point(p).add(o.getLocation().getNegative()).toVector();
            setImage(new GreenfootImage((int)path.abs()+o.getImage().getWidth(), h));
            setLocation((int)new Vector(o.getLocation()).add(new Vector(path).scale(0.5)).getX(),
            (int)new Vector(o.getLocation()).add(new Vector(path).scale(0.5)).getY());
            setRotation(path.getAngle());
            return getOneIntersectingObject(collider)==null;
        }
        protected java.util.List<Actor> collTo(Point p){
            Vector path = new Point(p).add(o.getLocation().getNegative()).toVector();
            setImage(new GreenfootImage((int)path.abs()+o.getImage().getWidth(), h));
            setLocation((int)new Vector(o.getLocation()).add(new Vector(path).scale(0.5)).getX(),
            (int)new Vector(o.getLocation()).add(new Vector(path).scale(0.5)).getY());
            setRotation(path.getAngle());
            return getIntersectingObjects(null);
        }
    }
}