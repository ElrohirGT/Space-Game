import java.util.ArrayList;

/**
 * Write a description of class EnemySpawnerConfiguration here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemySpawnerConfiguration  
{
    public EnemySpawnerConfiguration(int msCooldown, ArrayList<Wave> waves)
    {
        _msCooldown = msCooldown;
        _waves = waves;
    }

    private int _msCooldown;
    public int getMSCooldown() {
        return _msCooldown;
    }
    public void setMSCooldown(int msCooldown, ArrayList<Wave> waves)
    {
        _msCooldown = msCooldown;
        _waves = waves;
    }

    private ArrayList<Wave> _waves;
    public ArrayList<Wave> getWaves() {
        return _waves;
    }
    public void setWaves(ArrayList<Wave> waves)
    {
        _waves = waves;
    }
}
