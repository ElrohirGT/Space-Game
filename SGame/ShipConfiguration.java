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
    private int _ultimateCount;
    
    public ShipConfiguration(MovementConfiguration moveConfig, IGunBrain shipGun, int ultimateCount)
    {
        _moveConfig = moveConfig;
        _shipGun = shipGun;
        _ultimateCount = ultimateCount;
    }
    
    public int getUltimateCount() {
        return _ultimateCount;
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
