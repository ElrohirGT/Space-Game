import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Actor
{
    private ShipConfiguration _configuration;
    private IMovementBrain _movementBrain;
    private IGunBrain _gunBrain;

    public Ship(ShipConfiguration configuration)
    {
        _configuration = configuration;
        _movementBrain = new PlayerMovementBrain(configuration.getMovementConfiguration());
        _gunBrain = configuration.getShipGun();
        HelpMethods.scaleToWidth(this, 64);
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
        if (_gunBrain.shouldFire())
        {
            _gunBrain.fire(this, getRotation());
        }
    }

    /**
     * Test method for firing a shot.
     */
    public void fire()
    {
        _gunBrain.fire(this, getRotation());
    }
}
