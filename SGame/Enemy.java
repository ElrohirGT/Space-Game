import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private IMovementBrain _movementBrain;
    private ICollisionBrain<Enemy> _collisionBrain;
    private IEnemyManager _manager;

    public Enemy(IMovementBrain movementBrain, ICollisionBrain<Enemy> collisionBrain)
    {
        _movementBrain = movementBrain;
        _collisionBrain = collisionBrain;
    }

    public void setEnemyManager(IEnemyManager manager)
    {
        _manager = manager;
    }

    public boolean isTouchingBullet()
    {
        return isTouching(BasicBullet.class);
    }

    public void diesFromBullet()
    {
        removeTouching(BasicBullet.class);
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
        getWorld().removeObject(this);
        _manager.removeEnemy(this);
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
