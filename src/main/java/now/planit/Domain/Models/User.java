package now.planit.Domain.Models;


public class User {
  private final String name;
  private final String email;
  private final String password;


  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return name + " Email: " + email + "Password: " + password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
