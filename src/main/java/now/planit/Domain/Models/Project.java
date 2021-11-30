package now.planit.Domain.Models;


public class Project {
  private String name;
  private String start;
  private String finish;
  private String budget;

  public Project( String name, String start, String finish, String budget) {

    this.name = name;
    this.start = start;
    this.finish = finish;
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
