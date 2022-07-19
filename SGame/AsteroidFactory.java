import greenfoot.*;

/**
 * Write a description of class AsteroidFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AsteroidFactory implements IEnemyFactory
{
    AsteroidMovementBrain _baseMovementBrain;
    AsteroidCollisionBrain _baseCollisionBrain;
    MiniAsteroidFactory _factory;

    public AsteroidFactory(AsteroidMovementBrain baseMovementBrain, AsteroidCollisionBrain baseCollisionBrain, MiniAsteroidFactory factory)
    {
        _baseMovementBrain = baseMovementBrain;
        _baseCollisionBrain = baseCollisionBrain;
        _factory = factory;
    }

    @Override
    public Enemy getInstance() {
        return new Asteroid(new AsteroidMovementBrain(_baseMovementBrain), new AsteroidCollisionBrain(_baseCollisionBrain), _factory);
    }

}
