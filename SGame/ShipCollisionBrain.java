import greenfoot.*;

/**
 * Write a description of class ShipCollisionBrain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShipCollisionBrain  implements ICollisionBrain<Ship>
{

    private ShipCollisions _lastCollision;

    @Override
    public boolean isColliding(Ship body) {
        boolean isColliding = false;
        if (body.enemyColliding()) {
            _lastCollision = ShipCollisions.ENEMY;
            isColliding = true;
        }

        if (body.powerUpColliding()) {
            _lastCollision = ShipCollisions.POWER_UP;
            isColliding = true;
        }
        
        return isColliding;
    }

    @Override
    public void actOnCollision(Ship body) {
        switch (_lastCollision) {
            case ENEMY:
                Enemy enemy = body.getEnemyColliding();
                body.looseLife();
                enemy.diesFromPlayer();
                break;
            case POWER_UP:
                Actor power_up = body.getCollidingPowerUp();
                body.gainUltimatePoint();
                body.getWorld().removeObject(power_up);
            default:
                break;
        }
    }

    enum ShipCollisions {
        ENEMY,
        POWER_UP
    }
}
