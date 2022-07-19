import greenfoot.*;

/**
 * Write a description of class AsteroidFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AsteroidFactory implements IEnemyFactory
{
    AsteroidMovementBrain _baseInstance;

    public AsteroidFactory(AsteroidMovementBrain baseInstance)
    {
        _baseInstance = baseInstance;
    }

    @Override
    public Actor getInstance() {
        return new Asteroid(new AsteroidMovementBrain(_baseInstance));
    }

}
