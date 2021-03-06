import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import packages.ui.Text;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Actor
{
    private final int ROCKET_WIDTH = 64;

    private int _life = 3;
    private int _ultimateCount = 0;
    
    private IUltimateManager _ultimateManager;
    private ShipConfiguration _configuration;
    private IMovementBrain _movementBrain;
    private IGunBrain _gunBrain;
    private ICollisionBrain<Ship> _collisionBrain;

    private GreenfootImage[] _thrustRocketImages;
    private GreenfootImage[] _stillRocketImages;
    private SimpleTimer _lifeTimer = new SimpleTimer();

    GreenfootSound _fireSound = new GreenfootSound("fire_bullet.wav");

    public Ship(ShipConfiguration configuration, IUltimateManager ultimateManager)
    {
        _configuration = configuration;
        _ultimateManager = ultimateManager;
        _fireSound.setVolume(70);
        _movementBrain = new PlayerMovementBrain(configuration.getMovementConfiguration());
        _collisionBrain = new ShipCollisionBrain();
        _gunBrain = configuration.getShipGun();
        turn(-90);//This sets the ship facing up

        _thrustRocketImages = new GreenfootImage[_life+1];
        _stillRocketImages = new GreenfootImage[_life+1];

        for (int i = 0; i <= _life; i++) {
            var thrustImage = new GreenfootImage("rocket" + i + "lives.png");
            HelpMethods.scaleToWidth(thrustImage, ROCKET_WIDTH);
            _thrustRocketImages[i] = thrustImage;

            var stillImage = new GreenfootImage("rocketStill" + i + "lives.png");
            HelpMethods.scaleToWidth(stillImage, ROCKET_WIDTH);
            _stillRocketImages[i] = stillImage;
        }
    }

    public boolean powerUpColliding() {
        return isTouching(PowerUp.class);
    }
    public Actor getCollidingPowerUp() {
        return getOneIntersectingObject(PowerUp.class);
    }

    public void gainUltimatePoint() {
        _ultimateManager.incrementOrbCount();
        updateUltimateCounter();
    }

    public void updateUltimateCounter() {
        getWorld().getObjects(Text.class).get(0).setContent(_ultimateManager.getCurrentOrbCount() + "/" + _ultimateManager.getTotalOrbCount());
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
        if (--_life <= 0) {
            endGame();
        }
    }

    private void endGame()
    {
        Greenfoot.setWorld(new EndGame(_lifeTimer.millisElapsed()));
    }
    
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (_movementBrain.shouldMove())
        {
            setImage(_thrustRocketImages[_life]);
            _movementBrain.move(this);
        }
        else
        {
            setImage(_stillRocketImages[_life]);
        }
        if (_gunBrain.shouldFire())
        {
            _fireSound.play();
            _gunBrain.fire(this, getRotation());
        }

        if (_ultimateManager.shouldFireUltimate()) {
            _ultimateManager.fireUltimate(this);
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
