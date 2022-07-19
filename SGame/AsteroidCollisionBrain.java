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
    
    public AsteroidCollisionBrain(boolean startsCollidingWithBorder)
    {
        _justSpawned = startsCollidingWithBorder;
    }

    public AsteroidCollisionBrain(AsteroidCollisionBrain base)
    {
        _justSpawned = base.getJustSpawned();
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
            _action = AsteroidActions.DIES_FROM_AGE;
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
            default:
                break;
        }
    }
}
