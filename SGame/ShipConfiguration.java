/**
 * Write a description of class ShipConfiguration here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShipConfiguration  
{
    private MovementConfiguration _moveConfig;
    private IGunBrain _shipGun;
    
    public ShipConfiguration(MovementConfiguration moveConfig, IGunBrain shipGun)
    {
        _moveConfig = moveConfig;
        _shipGun = shipGun;
    }
    
    public IGunBrain getShipGun()
    {
        return _shipGun;
    }
    
    public MovementConfiguration getMovementConfiguration()
    {
        return _moveConfig;
    }
}
