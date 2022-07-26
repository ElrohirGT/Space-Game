/**
 * Write a description of class IUltimateManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IUltimateManager  
{
    public void incrementOrbCount();
    public int getCurrentOrbCount();
    public int getTotalOrbCount();
    public boolean shouldFireUltimate();
    public void fireUltimate(Ship body);
}
