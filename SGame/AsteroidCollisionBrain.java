import greenfoot.*;

/**
 * Write a description of class AsteroidCollisionBrain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AsteroidCollisionBrain implements ICollisionBrain<Enemy>
{
    boolean _justSpawned = true;
    AsteroidActions _action;
    GreenfootSound _bounceSound = new GreenfootSound("bounce.wav");
    
    public AsteroidCollisionBrain(boolean startsCollidingWithBorder)
    {
        _justSpawned = startsCollidingWithBorder;
        _bounceSound.setVolume(60);
    }

    public AsteroidCollisionBrain(AsteroidCollisionBrain base)
    {
        this(base.getJustSpawned());
    }

    public boolean getJustSpawned()
    {
        return _justSpawned;
    }


    @Override
    public boolean isColliding(Enemy body) {
        final boolean isTouchingWall = body.isAtEdge();
        final boolean isTouchingBullet = body.isTouchingBullet();

        var isColliding = false;
        if (!isTouchingWall)
        {
            _justSpawned = false;
        }
        if (isTouchingWall && !_justSpawned)
        {
            isColliding = true;
            _action = AsteroidActions.BOUNCE;
        }

        if (isTouchingBullet) {
            isColliding = true;
            _action = AsteroidActions.DIES_FROM_BULLET;
        }
        
        return isColliding;
    }

    @Override
    public void actOnCollision(Enemy body) {
        switch (_action) {
            case DIES_FROM_AGE:
                body.diesFromAge();
                break;
            case DIES_FROM_BULLET:
                body.diesFromBullet();
                break;
            case BOUNCE:
                _bounceSound.play();
                final int X = body.getX();
                final int Y = body.getY();
                final int ROTATION = 0 - body.getRotation();

                double xSpeed = body.getMovementSpeed() * Math.cos(Math.toRadians(ROTATION));
                double ySpeed = body.getMovementSpeed() * Math.sin(Math.toRadians(ROTATION));

                final int X_LIMIT = SinglePlayerLevel.WORLD_WIDTH - 2;
                final int Y_LIMIT = SinglePlayerLevel.WORLD_HEIGHT - 2;

                if (X <= 0 || X >= X_LIMIT) {
                    xSpeed *= -1;
                }
                if (Y <= 0 || Y >= Y_LIMIT) {
                    ySpeed *= -1;
                }

                int newX = clamp(X + (int)Math.round(xSpeed), 2, X_LIMIT - 2);
                int newY = clamp(Y + (int)Math.round(ySpeed), 2, Y_LIMIT - 2);

                double newRotation = Math.toDegrees(Math.atan2(ySpeed, xSpeed));
                body.setRotation(360 - (int)Math.round(newRotation));
                body.setLocation(newX, newY);
                
            default:
                break;
        }
    }

    private int clamp(int value, int min, int max)
    {
        return Math.max(min, Math.min(max, value));
    }
}
