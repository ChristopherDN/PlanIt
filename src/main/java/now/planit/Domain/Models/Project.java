package now.planit.Domain.Models;


public class Project {
  private String name;
  private String start;
  private String finish;
  private int budget;

  public Project( String name, String start, String finish, int budget) {

    this.name = name;
    this.start = start;
    this.finish = finish;
    this.budget = budget;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getFinish() {
    return finish;
  }

  public void setFinish(String finish) {
    this.finish = finish;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  @Override
  public String toString() {
    return "Project{" +
            ", name='" + name + '\'' +
            ", start='" + start + '\'' +
            ", finish='" + finish + '\'' +
            ", budget='" + budget + '\'' +
            '}';
  }
}
