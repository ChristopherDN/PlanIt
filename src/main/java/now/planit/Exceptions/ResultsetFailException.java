package now.planit.Exceptions;

public class ResultsetFailException extends Exception {

        private String resultsetFailMessage;

        public ResultsetFailException() {
            this.resultsetFailMessage = "Resultset could not be loaded";
        }

    public String getDBConnFailMessage(){
            return resultsetFailMessage;
        }

    }


