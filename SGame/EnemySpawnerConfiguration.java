
/**
 * Write a description of class EnemySpawnerConfiguration here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemySpawnerConfiguration  
{
    public EnemySpawnerConfiguration(int msCooldown, IWaveGenerator waveGenerator)
    {
        _msCooldown = msCooldown;
        _waveGenerator = waveGenerator;
    }

    private int _msCooldown;
    public int getMSCooldown() {
        return _msCooldown;
    }
    public void setMSCooldown(int msCooldown)
    {
        _msCooldown = msCooldown;
    }

    private IWaveGenerator _waveGenerator;
    public Wave getWave(int index) {
        return _waveGenerator.getWave(index);
    }
}
