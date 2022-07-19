/**
 * Write a description of class ShipCollisionBrain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShipCollisionBrain  implements ICollisionBrain<Ship>
{

    @Override
    public boolean isColliding(Ship body) {
        boolean isColliding = false;
        if (body.enemyColliding()) {
            isColliding = true;
        }
        
        return isColliding;
    }

    @Override
    public void actOnCollision(Ship body) {
        Enemy enemy = body.getEnemyColliding();
        body.looseLife();
        enemy.diesFromPlayer();
    }
}
