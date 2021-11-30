package now.planit.Domain.Models;


public class Project {
  private String projectName;
  private String startDate;
  private String finishDate;
  private String budget;

  public Project(String name, String startDate, String finishDate, String budget) {
    this.projectName = name;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.budget = budget;
  }

  public String getName() {
    return projectName;
  }

  public void setName(String name) {
    this.projectName = name;
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

  public String getBudget() {
    return budget;
  }

  public void setBudget(String budget) {
    this.budget = budget;
  }
}
