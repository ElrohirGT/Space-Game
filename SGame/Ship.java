import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Actor
{
    private ShipConfiguration _configuration = new ShipConfiguration();
    private IMovementBrain _movementBrain = new PlayerMovementBrain();

    public Ship()
    {   
        turn(-90);//This sets the ship facing up
    }
    
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (_movementBrain.shouldMove())
        {
            _movementBrain.move(this);
        }
    }
}
