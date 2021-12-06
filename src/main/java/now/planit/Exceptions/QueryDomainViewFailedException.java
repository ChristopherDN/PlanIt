package now.planit.Exceptions;

public class QueryDomainViewFailedException extends Exception{

    private String queryDomainFailedMessage = "The query in domain layer failed";

    public QueryDomainViewFailedException(String dbConnFailMessage) {
        this.queryDomainFailedMessage = queryDomainFailedMessage;
    }

    public String getDBConnFailMessage(){
        return queryDomainFailedMessage;
    }




}
