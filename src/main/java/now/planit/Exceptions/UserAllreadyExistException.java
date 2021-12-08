package now.planit.Exceptions;

public class UserAllreadyExistException extends Throwable {

    public UserAllreadyExistException(String message) {
        super(message);
    }
}
