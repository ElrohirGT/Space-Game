import greenfoot.*;

/**
 * Write a description of class PowerUpSpawner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUpSpawner implements ISpawner
{
    SimpleTimer _spawnTimer = new SimpleTimer();

    @Override
    public boolean shouldSpawn() {
        boolean spawnProbability = Greenfoot.getRandomNumber(100) < 100;
        boolean timerCompleted = _spawnTimer.millisElapsed() >= 5000;
        return spawnProbability && timerCompleted;
    }

    @Override
    public void spawn(World world) {
        
    }
    @Override
    public void spawn(World world, int x, int y) {
        PowerUp actor = new PowerUp();
        actor.setRotation(Greenfoot.getRandomNumber(360));
        world.addObject(actor, x, y);
        _spawnTimer.mark();
    }
}
