import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasicBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasicBullet extends Actor
{
    private BulletConfiguration _config;

    public BasicBullet(BulletConfiguration config)
    {
        _config = config;
        HelpMethods.scaleToWidth(this, 20);
    }

    /**
     * Act - do whatever the BasicBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move((int)_config.getMovementSpeed());
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
