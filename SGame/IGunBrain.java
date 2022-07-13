import greenfoot.*;

public interface IGunBrain  
{
    public boolean shouldFire();
    public void fire(Actor owner, int rotation);
}
