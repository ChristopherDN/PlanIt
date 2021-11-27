package now.planit.Domain.Models;


public class User {
  private String name;
  private String email;
  private String password;


  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return name + " Email: " + email + "Password: " + password;
  }




}
