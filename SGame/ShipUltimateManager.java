import greenfoot.*;

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

    GreenfootSound _ultimateOnSound = new GreenfootSound("ultimate_on.wav");
    GreenfootSound _ultimateOffSound = new GreenfootSound("ultimate_off.wav");

    public ShipUltimateManager(int totalOrbCount, EnemySpawner globalSpawner) {
        _totalOrbCount = totalOrbCount;
        _globalSpawner = globalSpawner;
    }

    public void fireUltimate(Ship body) {
        _ultimateOnSound.play();
        _ultimateIsActive = true;
        _globalSpawner.scaleAll(0.5);
        _globalSpawner.scaleSpeedAll(0.25);
        _ultimateTimer.mark();
        _orbCount = 0;
        body.updateUltimateCounter();
    }

    public void reverseUltimate() {
        _ultimateOffSound.play();
        _globalSpawner.scaleAll(2);
        _globalSpawner.scaleSpeedAll(4);
    }

    @Override
    public void incrementOrbCount() {
        _orbCount = Math.min(++_orbCount, _totalOrbCount);
    }

    @Override
    public boolean shouldFireUltimate() {
        boolean canFire = _orbCount == _totalOrbCount;
        boolean wantsToFire = Greenfoot.isKeyDown("q");

        if (_ultimateTimer.millisElapsed() >= 20_000 && _ultimateIsActive) {
            _ultimateIsActive = false;
            reverseUltimate();
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
