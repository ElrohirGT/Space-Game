package packages.physix;
import greenfoot.Actor;

/**
 * Represents any static objects that should interact with the PhysixObjects.
 * These objects do not move in any way by default and are mainly there to
 * differ between collidable objects and others.
 * 
 * @author RcCookie
 * 
 * @version 1.0
 */
public abstract class StaticObject extends Actor
{
    /**
     * Calls the run method. No other purpose. Final to fit the style of
     * PhysixObjects.
     */
    public final void act(){
        run();
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
}