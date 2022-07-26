import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUp extends Actor
{

    SimpleTimer _lifeTime = new SimpleTimer();
    AsteroidMovementBrain _movementBrain;

    private final int LIFE_DURATION = 8000;

    public PowerUp()
    {
        _movementBrain = new AsteroidMovementBrain(new MovementConfiguration(1, 0));
    }

    /**
     * Act - do whatever the PowerUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (_movementBrain.shouldMove()) {
            _movementBrain.move(this);
        }

        if (_lifeTime.millisElapsed() >= LIFE_DURATION) {
            getWorld().removeObject(this);
        }
    }
}
