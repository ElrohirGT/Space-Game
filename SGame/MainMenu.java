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
    
    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);

        World singlePlayerWorld = new SinglePlayerLevel();

        setBackground("space1.jpg");
        add(new Text("Alien Survival", 65, Color.RED), 1f/1.85, 1f/5);
        add(new Button("1 Jugador", singlePlayerWorld), 1f/2, 2f/5);
        add(new Button("2 Jugadores"), 1f/2, 1f/2);
        add(new Button("Tutorial"), 1f/2, 3f/5);
    }
}
