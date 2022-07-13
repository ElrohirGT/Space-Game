import greenfoot.*;
/**
 * Write a description of class HelpMethods here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpMethods  
{
    public static void scaleToWidth(Actor actor, int width)
    {
        scaleTo(actor, width, true);
    }
    public static void scaleToHeight(Actor actor, int height)
    {
        scaleTo(actor, height, false);
    }
    
    private static void scaleTo(Actor actor, int target, boolean targetIsWidth)
    {
        var image = actor.getImage();
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
        actor.setImage(image);
    }
}
