import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import greenfoot.*;

public class EnemySpawner implements ISpawner, IEnemyManager
{
    private int _currentWaveIndex = 0;
    private EnemySpawnerConfiguration _configuration;
    private SimpleTimer _internalTimer = new SimpleTimer();
    private ArrayList<Enemy> _currentWaveEnemies = new ArrayList<>();

    public EnemySpawner(EnemySpawnerConfiguration config)
    {
        _configuration = config;
    }

    @Override
    public boolean shouldSpawn() {
        boolean timerDone = _internalTimer.millisElapsed() > _configuration.getMSCooldown();
        boolean nextWaveAvailable = _configuration.getWaves().size() > _currentWaveIndex;
        return _currentWaveEnemies.isEmpty() && timerDone && nextWaveAvailable;
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
                int rotation = getRandomRotation(direction); //Range between 1-179, this way there are no 0 and 180 angles.

                switch (direction) {
                    case 0:// NORTH
                        x += Greenfoot.getRandomNumber(WORLD_WIDTH);
                        break;
                    case 1:// SOUTH
                        y = SinglePlayerLevel.WORLD_HEIGHT;
                        x += Greenfoot.getRandomNumber(WORLD_WIDTH);
                        break;
                    case 2:// EAST
                        x = SinglePlayerLevel.WORLD_WIDTH;
                        y += Greenfoot.getRandomNumber(WORLD_HEIGHT);
                        break;
                    case 3:// WEST
                        y += Greenfoot.getRandomNumber(WORLD_HEIGHT);
                        break;
                    default:
                        break;
                }

                Enemy newEnemy = factory.getInstance();
                addEnemy(newEnemy);
                newEnemy.setRotation(rotation);
                world.addObject(newEnemy, x, y);
            }
        }
        _currentWaveIndex++;
    }

    /**
     * Get's a random rotation for an object in a specified direction.
     * @param direction An int, 0 NORTH, 1 SOUTH, 2 EAST, 3 WEST
     * @return a random rotation in greenfoot units.
     */
    public static int getRandomRotation(int direction)
    {
        int rotation = Greenfoot.getRandomNumber(179) + 1; //Range between 1-179, this way there are no 0 and 180 angles.

        switch (direction) {
            case 1:// SOUTH
                rotation = -rotation;
                break;
            case 2:// EAST
                rotation += 90;
                break;
            case 3:// WEST
                rotation -= 90;
                break;
            default:
                break;
        }
        return rotation;
    }

    public void addEnemy(Enemy enemy)
    {
        _currentWaveEnemies.add(enemy);
        enemy.setEnemyManager(this);
    }

    @Override
    public void removeEnemy(Enemy enemy) {
        _currentWaveEnemies.remove(enemy);

        if (_currentWaveEnemies.isEmpty()) {
            _internalTimer.mark();
        }
    }

}
