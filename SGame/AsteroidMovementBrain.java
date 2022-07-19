import greenfoot.*;

public class AsteroidMovementBrain implements IMovementBrain
{
    MovementConfiguration _configuration;

    public AsteroidMovementBrain(MovementConfiguration configuration)
    {
        _configuration = configuration;
    }

    public AsteroidMovementBrain(AsteroidMovementBrain baseInstance)
    {
        _configuration = new MovementConfiguration(baseInstance.getConfiguration());
    }

    public MovementConfiguration getConfiguration()
    {
        return _configuration;
    }

    @Override
    public boolean shouldMove() {
        return true;
    }

    @Override
    public void move(Actor body) {
        body.move((int)_configuration.getMovementSpeed());
    }
    
}
