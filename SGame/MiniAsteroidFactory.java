import greenfoot.Greenfoot;

/**
 * Write a description of class MiniAsteroidFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniAsteroidFactory implements IEnemyFactory
{
    AsteroidMovementBrain _baseMovementBrain;
    AsteroidCollisionBrain _baseCollisionBrain;

    public MiniAsteroidFactory(AsteroidMovementBrain baseMovementBrain, AsteroidCollisionBrain baseCollisionBrain)
    {
        _baseMovementBrain = baseMovementBrain;
        _baseCollisionBrain = baseCollisionBrain;
    }

    @Override
    public Enemy getInstance() {
        return new MiniAsteroid(new AsteroidMovementBrain(_baseMovementBrain), new AsteroidCollisionBrain(_baseCollisionBrain));
    }
}
