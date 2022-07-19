import greenfoot.*;
/**
 * Write a description of class ICollisionBrain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface ICollisionBrain<T extends Actor>  
{
    public boolean isColliding(T body);
    public void actOnCollision(T body);
}
