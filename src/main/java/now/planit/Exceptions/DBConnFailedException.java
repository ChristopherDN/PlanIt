package now.planit.Exceptions;

public class DBConnFailedException extends Exception {

    private String dbConnFailMessage = "Database unavaiable";

    public DBConnFailedException(String DBConnFailMessage) {
        this.dbConnFailMessage = dbConnFailMessage;
    }

    public String getDBConnFailMessage(){
        return dbConnFailMessage;
    }

}
