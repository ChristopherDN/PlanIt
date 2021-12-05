package now.planit.Exceptions;

public class DBConnFailedException extends Exception {

    private String dbConnFailMessage;

    public DBConnFailedException(String DBConnFailMessage) {
        this.dbConnFailMessage = dbConnFailMessage;
    }

    public String getDBConnFailMessage(){
        return dbConnFailMessage;
    }

}
