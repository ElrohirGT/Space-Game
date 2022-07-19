import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Actor
{
    private final int ROCKET_WIDTH = 64;

    private int life = 3;

    private ShipConfiguration _configuration;
    private IMovementBrain _movementBrain;
    private IGunBrain _gunBrain;
    private ICollisionBrain _collisionBrain;

    private GreenfootImage _thrustRocketImage;
    private GreenfootImage _stillRocketImage;

    public Ship(ShipConfiguration configuration)
    {
        _configuration = configuration;
        _movementBrain = new PlayerMovementBrain(configuration.getMovementConfiguration());
        _collisionBrain = new ShipCollisionBrain();
        _gunBrain = configuration.getShipGun();
        turn(-90);//This sets the ship facing up

        _thrustRocketImage = new GreenfootImage("rocket.png");
        HelpMethods.scaleToWidth(_thrustRocketImage, ROCKET_WIDTH);
        _stillRocketImage = new GreenfootImage("rocketStill.png");
        HelpMethods.scaleToWidth(_stillRocketImage, ROCKET_WIDTH);
    }

    public boolean enemyColliding()
    {
        return isTouching(Enemy.class);
    }
    public Enemy getEnemyColliding()
    {
        return (Enemy)getOneIntersectingObject(Enemy.class);
    }

    public void looseLife()
    {
        if (--life <= 0) {
            endGame();
        }
    }

    private void endGame()
    {
        Greenfoot.setWorld(new EndGame());
    }
    
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (_movementBrain.shouldMove())
        {
            setImage(_thrustRocketImage);
            _movementBrain.move(this);
        }
        else
        {
            setImage(_stillRocketImage);
        }
        if (_gunBrain.shouldFire())
        {
            _gunBrain.fire(this, getRotation());
        }

        if (_collisionBrain.isColliding(this))
        {
            _collisionBrain.actOnCollision(this);
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
