import greenfoot.*;
import java.util.HashMap;

public class PlayerMovementBrain implements IMovementBrain {
    MovementConfiguration _configuration;
    private double _moveMagnitude = 0;
    private double _turnDeg = 0;

    private HashMap<String, PlayerAction> _keyToPlayerActionMapper = new HashMap<>();
    private String[] _actionKeys = new String[] { "w", "a", "s", "d", "space" };;

    public PlayerMovementBrain(MovementConfiguration moveConfig) {
        _configuration = moveConfig;
        _keyToPlayerActionMapper.put("w", PlayerAction.FORWARD);
        _keyToPlayerActionMapper.put("a", PlayerAction.LEFT);
        _keyToPlayerActionMapper.put("s", PlayerAction.BACKWARDS);
        _keyToPlayerActionMapper.put("d", PlayerAction.RIGHT);
        _keyToPlayerActionMapper.put("space", PlayerAction.FIRE);
    }
    
    @Override
    public int getMovementSpeed()
    {
        return Math.round(_configuration.getMovementSpeed());
    }
    @Override
    public void setMovementSpeed(int newMovementSpeed) {
        _configuration.setMovementSpeed(newMovementSpeed);
    }

    @Override
    public boolean shouldMove() {
        final PlayerAction[] actions = getKeyboardInput();
        boolean needsToMove = false;
        for (PlayerAction action : actions)
        {
            switch (action) {
            case FORWARD:
                _moveMagnitude = _configuration.getMovementSpeed();
                needsToMove = true;
                break;
            case BACKWARDS:
                _moveMagnitude = -_configuration.getMovementSpeed();
                needsToMove = true;
                break;
            case LEFT:
                _turnDeg -= _configuration.getTurnSpeed();
                needsToMove = true;
                break;
            case RIGHT:
                _turnDeg += _configuration.getTurnSpeed();
                needsToMove = true;
                break;
            default:
                break;
            }
        }
        return needsToMove;
    }

    private PlayerAction[] getKeyboardInput() {
        PlayerAction[] actions = new PlayerAction[_actionKeys.length];
        for(int i = 0; i < _actionKeys.length; i++)
        {
            final var actionKey = _actionKeys[i];
            final boolean isKeyDown = Greenfoot.isKeyDown(actionKey);
            if (!isKeyDown)
            {
                actions[i] = PlayerAction.NONE;
                continue;
            }
            final PlayerAction action = _keyToPlayerActionMapper.get(actionKey);
            actions[i] = (action ==null) ? PlayerAction.NONE : action;
        }
        return actions;
    }

    @Override
    public void move(Actor body) {
        body.move((int)_moveMagnitude);
        body.turn((int)_turnDeg);
        
        _moveMagnitude = 0;
        _turnDeg = 0;
    }
}
