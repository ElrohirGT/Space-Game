import greenfoot.*;


public interface ISpawner  
{
    public boolean shouldSpawn();
    public void spawn(World world); 
    public void spawn(World world, int x, int y);
}
