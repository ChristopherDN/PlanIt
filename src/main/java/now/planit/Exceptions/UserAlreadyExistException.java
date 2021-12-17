package now.planit.Exceptions;

public class UserAlreadyExistException extends Throwable {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
