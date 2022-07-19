import greenfoot.*;
/**
 * Write a description of class HelpMethods here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpMethods  
{
    public static void setRandomRotation(Enemy accordingTo, Enemy target, int range)
    {
        int delta = Greenfoot.getRandomNumber(range) - 90;
        int baseRotation = accordingTo.getRotation();

        target.setRotation(baseRotation + delta);
    }

    public static void scaleToWidth(GreenfootImage image, int width)
    {
        scaleTo(image, width, true);
    }
    public static void scaleToHeight(GreenfootImage image, int height)
    {
        scaleTo(image, height, false);
    }
    
    private static void scaleTo(GreenfootImage image, int target, boolean targetIsWidth)
    {
        if (targetIsWidth)
        {
            double ratio = (double)image.getHeight() / image.getWidth();
            image.scale(target, (int)(target*ratio));
        }
        else
        {
            double ratio = (double)image.getWidth() / image.getHeight();
            image.scale((int)(target*ratio), target);
        }
    }
}
