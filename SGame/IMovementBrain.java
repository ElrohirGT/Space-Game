import greenfoot.*;

public interface IMovementBrain {
    public boolean shouldMove();
    public void move(Actor body);
    public int getMovementSpeed();
}
