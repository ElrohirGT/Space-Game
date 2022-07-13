import greenfoot.*;

/**
 * Write a description of class PlayerShipGun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerShipGun implements IGunBrain
{
    private GunConfiguration _configuration;
    private SimpleTimer _reloadTimer = new SimpleTimer();

    public PlayerShipGun(GunConfiguration configuration)
    {
        _configuration = configuration;
    }

    @Override
    public boolean shouldFire() {
        return Greenfoot.isKeyDown("space") && _reloadTimer.millisElapsed() > _configuration.getReloadTime();
    }

    @Override
    public void fire(Actor owner, int rotation) {
        // TODO with a little bit of math the bullet could come from the tip but I don't want to to that right now.
        int x = owner.getX();
        int y = owner.getY();
        owner.getWorld().addObject(_configuration.getBulletFactory().createNew(rotation), x, y);
        _reloadTimer.mark();
    }
    
}
