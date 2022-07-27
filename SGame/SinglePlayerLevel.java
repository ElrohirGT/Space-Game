import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import packages.ui.Text;
import packages.ui.UIWorld;

/**
 * Write a description of class SinglePlayerLevel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SinglePlayerLevel extends UIWorld
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
        add(new Text("0/4", Color.GREEN), 0.9, 0.9);
        setPaintOrder(BasicBullet.class, Ship.class);

        _spawners = spawners;
    }

    @Override
    public void act()
    {
        if (!MainMenu.music.isPlaying()) {
            MainMenu.music.play();
        }

        for (ISpawner spawner : _spawners) {
            if (spawner.shouldSpawn()) {
                spawner.spawn(this);
            }
        }
    }
}
