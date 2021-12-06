package now.planit.Exceptions;

public class QueryDataFailException extends Exception {

        private String queryDataFailExceptionMessage = "The query in data layer failed.";

        public QueryDataFailException() {
            this.queryDataFailExceptionMessage = queryDataFailExceptionMessage;
        }

    public String getDBConnFailMessage(){
            return queryDataFailExceptionMessage;
        }

    }


