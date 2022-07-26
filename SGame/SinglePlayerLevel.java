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
    private Text _scoreText;
    private int _score = 0;

    /**
     * Constructor for objects of class SinglePlayerLevel.
     * 
     */
    public SinglePlayerLevel(Ship playerShip, ISpawner[] spawners)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        addObject(playerShip, WORLD_WIDTH/2,WORLD_HEIGHT/2);
        _scoreText = new Text("0 pts", Color.GREEN);
        add(new Text("0/5", Color.GREEN), 0.9, 0.9);
        add(_scoreText, 0.9, 0.1);
        setPaintOrder(BasicBullet.class, Ship.class);

        _spawners = spawners;
    }

    public void addPoints(int points) {
        _score += points;
        _scoreText.setContent(_score + " pts");
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
