package camera;
import greenfoot.*;
/**
 * This is the Ghost class. It is part of the camera package and
 * is used to display actors on the camera screen.
 * 
 * @author RcCookie
 * @version 0.1
 */
public class Ghost extends Actor
{
    /**
     * Constructs a new ghost by copying the given actors
     * image and rotation. Has to be positioned by constructing
     * class.
     * 
     * @param h The actor the ghost should copy
     */
    public Ghost(Actor h){
        setImage(h.getImage());
        setRotation(h.getRotation());
    }

    /**
     * Constructs a new ghost by copying the given actors
     * image and rotation. Scales the image of the actor to the
     * given value. Has to be positioned by constructing
     * class.
     * 
     * @param h The actor the ghost should copy
     */
    public Ghost(Actor h, double scale){
        GreenfootImage temp = h.getImage();
        temp.scale((int)((double)temp.getWidth()*scale), (int)((double)temp.getHeight()*scale));
        setImage(temp);
        setRotation(h.getRotation());
    }

    /**
     * Constructs a new ghost by copying the given actors
     * image. Has to be positioned by constructing
     * class.
     * 
     * @param h The actor the ghost should copy
     */
    public Ghost(GreenfootImage i){
        setImage(i);
    }
}