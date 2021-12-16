package now.planit.Exceptions;

public class DBConnFailedException extends RuntimeException {

    public DBConnFailedException(String message) {
        super(message);
    }


}
