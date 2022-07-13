/**
 * Write a description of class BulletConfiguration here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletConfiguration  
{
    private MovementConfiguration _movementConfiguration;
    private int _damage;
    
    public BulletConfiguration(MovementConfiguration moveConfig, int damage)
    {
        _damage = damage;
        _movementConfiguration = moveConfig;
    }
    
    public float getMovementSpeed()
    {
        return _movementConfiguration.getMovementSpeed();
    }
}
