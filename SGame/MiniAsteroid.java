import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniAsteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniAsteroid extends Enemy
{
    public MiniAsteroid(IMovementBrain movementBrain, ICollisionBrain<Enemy> collisionBrain)
    {
        super(movementBrain, collisionBrain);

        GreenfootImage image = getImage();
        HelpMethods.scaleToWidth(image, 32);
        setImage(image);
    }
}
