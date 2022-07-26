import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroid extends Enemy
{
    private final int CHILDREN_QUANTITY = 2;
    private IEnemyManager _manager;
    private MiniAsteroidFactory _factory;

    public Asteroid(IMovementBrain movementBrain, ICollisionBrain<Enemy> collisionBrain, MiniAsteroidFactory factory, ISpawner powerUpSpawner)
    {
        super(movementBrain, collisionBrain, powerUpSpawner);
        _factory = factory;
    }

    @Override
    public void setEnemyManager(IEnemyManager manager)
    {
        super.setEnemyManager(manager);
        _manager = manager;
    }

    @Override
    public void diesFromBullet()
    {
        World world = getWorld();

        Enemy[] children = createChildren();
        for (Enemy child : children) {
            _manager.addEnemy(child);
            world.addObject(child, getX(), getY());
        }

        super.diesFromBullet();
    }

    public Enemy[] createChildren()
    {
        Enemy[] children = new Enemy[CHILDREN_QUANTITY];
        for (int i = 0; i < CHILDREN_QUANTITY; i++) {
            var child = _factory.getInstance();
            HelpMethods.setRandomRotation(this, child, 90+45);

            children[i] = child;
        }
        return children;
    }

    @Override
    public int getPointsWorth() {
        return 100;
    }
}
