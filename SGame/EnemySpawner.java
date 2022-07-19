import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import greenfoot.*;

public class EnemySpawner implements ISpawner, IEnemyManager
{
    private final int MS_COOLDOWN = 500;
    private int _currentWaveIndex = 0;
    private EnemySpawnerConfiguration _configuration;
    private SimpleTimer _internalTimer = new SimpleTimer();
    private ArrayList<Actor> _currentWaveEnemies = new ArrayList<>();

    public EnemySpawner(EnemySpawnerConfiguration config)
    {
        _configuration = config;
    }

    @Override
    public boolean shouldSpawn() {
        boolean timerDone = _internalTimer.millisElapsed() > _configuration.getMSCooldown();
        return _currentWaveEnemies.isEmpty() && timerDone;
    }

    @Override
    public void spawn(World world) {
        Hashtable<IEnemyFactory, Integer> enemies = _configuration.getWaves().get(_currentWaveIndex).getEnemies();
        Set<IEnemyFactory> keys = enemies.keySet();
        final int WORLD_WIDTH = world.getWidth();
        final int WORLD_HEIGHT = world.getHeight();

        for (IEnemyFactory factory : keys) {
            for (int i = 0; i < enemies.get(factory); i++)
            {
                int direction = Greenfoot.getRandomNumber(4);
                int x = 0;
                int y = 0;
                int rotation = Greenfoot.getRandomNumber(179) + 1; //Range between 1-179, this way there are no 0 and 180 angles.

                switch (direction) {
                    case 0:// NORTH
                        x += Greenfoot.getRandomNumber(WORLD_WIDTH);
                        break;
                    case 1:// SOUTH
                        y = SinglePlayerLevel.WORLD_HEIGHT;
                        x += Greenfoot.getRandomNumber(WORLD_WIDTH);
                        rotation = -rotation;
                        break;
                    case 2:// EAST
                        x = SinglePlayerLevel.WORLD_WIDTH;
                        y += Greenfoot.getRandomNumber(WORLD_HEIGHT);
                        rotation += 90;
                        break;
                    case 3:// WEST
                        y += Greenfoot.getRandomNumber(WORLD_HEIGHT);
                        rotation -= 90;
                        break;
                    default:
                        break;
                }

                Actor newEnemy = factory.getInstance();
                _currentWaveEnemies.add(newEnemy);
                newEnemy.setRotation(rotation);
                world.addObject(newEnemy, x, y);
            }
        }
        _currentWaveIndex++;
    }

    @Override
    public void removeEnemy(Actor target) {
        _currentWaveEnemies.remove(target);

        if (_currentWaveEnemies.isEmpty()) {
            _internalTimer.mark();
        }
    }

}
