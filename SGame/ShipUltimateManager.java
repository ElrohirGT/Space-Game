import greenfoot.Greenfoot;

/**
 * Write a description of class ShipUltimateManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShipUltimateManager implements IUltimateManager
{
    private int _orbCount = 0;
    private int _totalOrbCount = 0;
    private EnemySpawner _globalSpawner;
    private SimpleTimer _ultimateTimer = new SimpleTimer();
    boolean _ultimateIsActive = false;

    public ShipUltimateManager(int totalOrbCount, EnemySpawner globalSpawner) {
        _totalOrbCount = totalOrbCount;
        _globalSpawner = globalSpawner;
    }

    public void fireUltimate(Ship body) {
        _ultimateIsActive = true;
        _globalSpawner.scaleAll(0.5);
        _globalSpawner.scaleSpeedAll(0.5);
        _ultimateTimer.mark();
        _orbCount = 0;
        body.updateUltimateCounter();
    }

    public void reverseUltimate() {
        _globalSpawner.scaleAll(2);
        _globalSpawner.scaleSpeedAll(2);
    }

    @Override
    public void incrementOrbCount() {
        _orbCount++;
    }

    @Override
    public boolean shouldFireUltimate() {
        boolean canFire = _orbCount == _totalOrbCount;
        boolean wantsToFire = Greenfoot.isKeyDown("q");

        if (_ultimateTimer.millisElapsed() >= 20_000 && _ultimateIsActive) {
            reverseUltimate();
            _ultimateIsActive = false;
        }
        
        return canFire && wantsToFire && !_ultimateIsActive;
    }

    @Override
    public int getCurrentOrbCount() {
        return _orbCount;
    }

    @Override
    public int getTotalOrbCount() {
        return _totalOrbCount;
    }
    
}
