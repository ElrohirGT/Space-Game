import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import packages.ui.*;

/**
 * Write a description of class EndGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndGame extends UIWorld
{

    /**
     * Constructor for objects of class EndGame.
     * 
     */
    public EndGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1080, 720, 1);

        MainMenu mainMenu = new MainMenu();
        World singlePlayerWorld = mainMenu.getSinglePlayerWorld();

        add(new Text("Thanks for Playing!", 65, Color.RED), 1f/1.85, 1f/5);
        add(new Button("Restart", singlePlayerWorld), 1f/2, 2f/5);
        add(new Button("Main Menu", mainMenu), 1f/2, 3f/5);
        add(new Button("Tutorial"), 1f/2, 4f/5);
    }
}
