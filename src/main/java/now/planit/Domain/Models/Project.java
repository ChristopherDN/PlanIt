package now.planit.Domain.Models;


public class Project {
  private String name;
  private String start;
  private String finish;
  private int hours;
  private int cost;
  private int budget;

  public Project(String name, String start, String finish, int budget, int anInt, int rsInt) {

    this.name = name;
    this.start = start;
    this.finish = finish;
    this.budget = budget;
  }

  public Project(String name, String start, String finish, int hours, int cost) {
    this.name = name;
    this.start = start;
    this.finish = finish;
    this.hours = hours;
    this.cost = cost;
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

  public int getHours() {
    return hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
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
