import java.util.Hashtable;

/**
 * Write a description of class WaveGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaveGenerator implements IWaveGenerator
{
    IEnemyFactory _factory;

    /**
     * Constructor for objects of class WaveGenerator
     */
    public WaveGenerator(IEnemyFactory factory)
    {
        _factory = factory;
    }

    public Wave getWave(int index) {
        var enemies = new Hashtable<IEnemyFactory, Integer>();
        enemies.put(_factory, fibonacci(index));
        return new Wave(enemies);
    }

    int fibonacci(int index) {
        int twoBefore = 0;
        int oneBefore = 1;
        int sum = 0;

        for (int i = 0; i < index; i++) {
            sum = twoBefore + oneBefore;
            twoBefore = oneBefore;
            oneBefore = sum;
        }

        return sum;
    }
}
