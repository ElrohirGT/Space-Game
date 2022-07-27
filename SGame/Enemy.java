import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends Actor
{
    private IMovementBrain _movementBrain;
    private ICollisionBrain<Enemy> _collisionBrain;
    private IEnemyManager _manager;
    private ISpawner _powerUpSpawner;

    public Enemy(IMovementBrain movementBrain, ICollisionBrain<Enemy> collisionBrain, ISpawner powerUpSpawner)
    {
        _movementBrain = movementBrain;
        _collisionBrain = collisionBrain;
        _powerUpSpawner = powerUpSpawner;
    }

    public void setEnemyManager(IEnemyManager manager)
    {
        _manager = manager;
    }

    public int getMovementSpeed()
    {
        return _movementBrain.getMovementSpeed();
    }
    public void setMovementSpeed(int newMovementSpeed) {
        _movementBrain.setMovementSpeed(newMovementSpeed);
    }

    public boolean isTouchingBullet()
    {
        return isTouching(BasicBullet.class);
    }

    public void diesFromBullet()
    {
        removeTouching(BasicBullet.class);
        if (_powerUpSpawner.shouldSpawn()) {
            _powerUpSpawner.spawn(getWorld(), getX(), getY());
        }
        dies();
    }

    public void diesFromAge()
    {
        dies();
    }

    public void diesFromPlayer()
    {
        dies();
    }

    private void dies()
    {
        _manager.removeEnemy(this);
        getWorld().removeObject(this);
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

        if (_collisionBrain.isColliding(this))
        {
            _collisionBrain.actOnCollision(this);
        }
    }
}
