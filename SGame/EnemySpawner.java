import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import greenfoot.*;

public class EnemySpawner implements ISpawner, IEnemyManager
{
    private int _currentWaveIndex = 1;
    private EnemySpawnerConfiguration _configuration;
    private SimpleTimer _timerBetweenWaves = new SimpleTimer();
    private SimpleTimer _waveTimer = new SimpleTimer();
    private ArrayList<Enemy> _currentWaveEnemies = new ArrayList<>();
    private ArrayList<Enemy> _speedModifiedEnemies = new ArrayList<>();
    private ArrayList<Enemy> _scaleModifiedEnemies = new ArrayList<>();
    private boolean _scaleModified = false;
    private boolean _speedModified = false;

    public EnemySpawner(EnemySpawnerConfiguration config)
    {
        _configuration = config;
    }

    public void scaleAll(double factor) {
        if (_scaleModified) {
            for (Enemy enemy : _scaleModifiedEnemies) {
                var image = enemy.getImage();
                double width = image.getWidth();
                int newWidth = (int)Math.round(width * factor);
                HelpMethods.scaleToWidth(image, newWidth);
                enemy.setImage(image);
            }
            _scaleModifiedEnemies.clear();
        }
        else {
            for (Enemy enemy : _currentWaveEnemies) {
                var image = enemy.getImage();
                double width = image.getWidth();
                int newWidth = (int)Math.round(width * factor);
                HelpMethods.scaleToWidth(image, newWidth);
                enemy.setImage(image);
                _scaleModifiedEnemies.add(enemy);
            }
        }
        _scaleModified = !_scaleModified;
    }

    public void scaleSpeedAll(double factor) {
        if (_speedModified) {
            for (Enemy enemy : _speedModifiedEnemies) {
                double speed = enemy.getMovementSpeed();
                enemy.setMovementSpeed((int)Math.round(speed * factor));
            }
            
            _speedModifiedEnemies.clear();
        }
        else {
            for (Enemy enemy : _currentWaveEnemies) {
                double speed = enemy.getMovementSpeed();
                enemy.setMovementSpeed((int)Math.round(speed * factor));
                _speedModifiedEnemies.add(enemy);
            }
        }
        _speedModified = !_speedModified;
    }

    @Override
    public boolean shouldSpawn() {
        boolean timeBetweenWaves = _timerBetweenWaves.millisElapsed() > _configuration.getMSCooldown();
        boolean waveTimerIsDone = _waveTimer.millisElapsed() > 20_000;
        // boolean nextWaveAvailable = _configuration.getWaves().size() > _currentWaveIndex;
        return (_currentWaveEnemies.isEmpty() && timeBetweenWaves) || waveTimerIsDone;
    }

    @Override
    public void spawn(World world) {
        _waveTimer.mark();
        Hashtable<IEnemyFactory, Integer> enemies = _configuration.getWave(_currentWaveIndex).getEnemies();
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

                spawn(world, x, y);

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
        _scaleModifiedEnemies.remove(enemy);
        _speedModifiedEnemies.remove(enemy);

        if (_currentWaveEnemies.isEmpty()) {
            _timerBetweenWaves.mark();
        }
    }

    @Override
    public void spawn(World world, int x, int y) {

    }

}
