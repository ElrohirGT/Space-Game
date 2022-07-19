import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroid extends Actor
{
    private IMovementBrain _movementBrain;
    private IEnemyManager _manager;

    public Asteroid(IMovementBrain movementBrain)
    {
        _movementBrain = movementBrain;
    }

    /**
     * Act - do whatever the Asteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (_movementBrain.shouldMove()) {
            _movementBrain.move(this);
        }
    }
}
