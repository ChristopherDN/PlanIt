package now.planit.Domain.Models;


public class Subtask {
  private String taskName;
  private String startDate;
  private String finishDate;
  private int cost;

  public Task(String taskName, String startDate, String finishDate, int cost) {
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

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }
}
