import greenfoot.*;

public class BasicBulletFactory implements IBulletFactory
{
    BulletConfiguration _bulletConfig;

    public BasicBulletFactory(BulletConfiguration bulletConfig)
    {
        _bulletConfig = bulletConfig;
    }

    @Override
    public Actor createNew(int rotation)
    {
        BasicBullet bullet = new BasicBullet(_bulletConfig);
        bullet.setRotation(rotation);
        return bullet;
    }
    
}
