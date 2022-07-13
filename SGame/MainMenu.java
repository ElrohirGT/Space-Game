import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import packages.ui.*;

/**
 * The Main Menu of the game
 * 
 * @author Flavio Galan
 * @version (a version number or a date)
 */
public class MainMenu extends UIWorld
{
    public static final int WORLD_WIDTH = 1024;
    public static final int WORLD_HEIGHT = 720;
    
    private final float PLAYER_MOVEMENT_SPEED = 5;
    private final float PLAYER_TURN_SPEED = 5;
    
    private final float BULLET_MOVEMENT_SPEED = 5;
    private final float BULLET_TURN_SPEED = 5;
    private final int BULLET_DAMAGE = 1;
    
    private final int GUN_RELOAD_MS = 500;
    
    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);

        
        MovementConfiguration mBulletConfig = new MovementConfiguration(BULLET_MOVEMENT_SPEED, BULLET_TURN_SPEED);
        BulletConfiguration bulletConfig = new BulletConfiguration(mBulletConfig, BULLET_DAMAGE);
        var bulletFactory = new BasicBulletFactory(bulletConfig);
        GunConfiguration gunConfig = new GunConfiguration(bulletFactory, GUN_RELOAD_MS);
        IGunBrain defaultGun = new PlayerShipGun(gunConfig);
        
        MovementConfiguration mShipConfig = new MovementConfiguration(PLAYER_MOVEMENT_SPEED, PLAYER_TURN_SPEED);
        ShipConfiguration sConfig = new ShipConfiguration(mShipConfig, defaultGun);
        Ship ship = new Ship(sConfig);
        
        World singlePlayerWorld = new SinglePlayerLevel(ship);

        setBackground("space1.jpg");
        add(new Text("Alien Survival", 65, Color.RED), 1f/1.85, 1f/5);
        add(new Button("1 Jugador", singlePlayerWorld), 1f/2, 2f/5);
        add(new Button("2 Jugadores"), 1f/2, 1f/2);
        add(new Button("Tutorial"), 1f/2, 3f/5);
    }
}
