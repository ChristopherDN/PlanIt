package now.planit.Domain.Services;


public class ExceptionService extends Throwable {
  public ExceptionService(String message) {
    super(message);
  }
}
