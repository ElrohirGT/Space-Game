import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SinglePlayerLevel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SinglePlayerLevel extends World
{
    public static final int WORLD_WIDTH = 1024;
    public static final int WORLD_HEIGHT = 720;

    private ISpawner[] _spawners;

    /**
     * Constructor for objects of class SinglePlayerLevel.
     * 
     */
    public SinglePlayerLevel(Ship playerShip, ISpawner[] spawners)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        addObject(playerShip, WORLD_WIDTH/2,WORLD_HEIGHT/2);
        setPaintOrder(BasicBullet.class, Ship.class);

        _spawners = spawners;
    }

    @Override
    public void act()
    {
        for (ISpawner spawner : _spawners) {
            if (spawner.shouldSpawn()) {
                spawner.spawn(this);
            }
        }
    }
}
