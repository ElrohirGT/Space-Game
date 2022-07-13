/**
 * Write a description of class GunConfiguration here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GunConfiguration  
{
    private IBulletFactory _bulletFactory;
    private int _reloadTime;

    public GunConfiguration(IBulletFactory bulletFactory, int reloadMs)
    {
        _bulletFactory = bulletFactory;
        _reloadTime = reloadMs;
    }

    public int getReloadTime()
    {
        return _reloadTime;
    }

    public IBulletFactory getBulletFactory()
    {
        return _bulletFactory;
    }
}
