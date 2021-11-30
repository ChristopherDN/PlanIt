package now.planit.Domain.Models;


public class Task {
  private String taskName;
  private String startDate;
  private String finishDate;
  private String cost;

  public Task(String taskName, String startDate, String finishDate, String cost) {
    this.taskName = taskName;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.cost = cost;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(String finishDate) {
    this.finishDate = finishDate;
  }

  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }
}
