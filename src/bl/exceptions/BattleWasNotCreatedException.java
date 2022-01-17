package bl.exceptions;

public class BattleWasNotCreatedException extends RuntimeException {
    public BattleWasNotCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
