import greenfoot.*;

public class MovementCalculator {
    public static Vector calculateDelta(double angleInDeg, double magnitud)
    {
        double radians = Math.toRadians(angleInDeg);
        double x = magnitud * Math.cos(radians);
        double y = magnitud * Math.sin(radians);

        return new Vector(x, y);
    }
    public static Vector getPositionFromActor(Actor body)
    {
        return new Vector(body.getX(), body.getY());
    }
}
