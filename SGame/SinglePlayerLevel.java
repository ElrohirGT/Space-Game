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
    /**
     * Constructor for objects of class SinglePlayerLevel.
     * 
     */
    public SinglePlayerLevel(Ship playerShip)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        addObject(playerShip, WORLD_WIDTH/2,WORLD_HEIGHT/2);
        setPaintOrder(Ship.class, BasicBullet.class);
    }
}
