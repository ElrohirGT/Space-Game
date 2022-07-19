public class MovementConfiguration {
    
    public MovementConfiguration(MovementConfiguration baseInstance)
    {
        _movementSpeed = baseInstance.getMovementSpeed();
        _turnSpeed = baseInstance.getTurnSpeed();
    }

    public MovementConfiguration(float movementSpeed, float turnSpeed)
    {
        _movementSpeed = movementSpeed;
        _turnSpeed = turnSpeed;
    }
    
    private float _movementSpeed;
    public float getMovementSpeed(){ return _movementSpeed; }
    public void setMovementSpeed(float newSpeed){ _movementSpeed = newSpeed; }

    private float _turnSpeed;
    public float getTurnSpeed(){ return _turnSpeed; }
    public void setTurnSpeed(float newSpeed){ _turnSpeed = newSpeed; }
}
