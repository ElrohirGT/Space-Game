import java.util.Hashtable;
import greenfoot.*;

/**
 * Write a description of class Wave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wave  
{
    private Hashtable<IEnemyFactory, Integer> _enemies;
    public Wave(Hashtable<IEnemyFactory, Integer> enemies)
    {
        _enemies = enemies;
    }

    public Hashtable<IEnemyFactory, Integer> getEnemies() {
        return _enemies;
    }
    public void setEnemies(Hashtable<IEnemyFactory, Integer> enemies) {
        _enemies = enemies;
    }
}
