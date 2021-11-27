package now.planit.Data.Repo;



import now.planit.Data.Utility.DBManager;
import now.planit.Domain.Models.Project;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
public class RepoProject{
  Connection connection;
  PreparedStatement ps;
  boolean bol;
  ResultSet rs;
  ArrayList<Project> products = new ArrayList<>();
  int userid;


  public void query(String sqlCommand) {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
      bol = ps.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public ResultSet load(String sqlCommand)  {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
      rs = ps.executeQuery();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      //throw new ExceptionService(ex.getMessage());
      //Chose not to use ExceptionService, and instead catch as early as possible.
    }
    return rs;
  }
/*
  public ArrayList<Project> resultsetToArray(ResultSet rs) {
    try {
      products.clear();
      while (rs.next()) {
        products.add(new Project(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return products;
  }
*/
  public int rsToId(ResultSet rs){
    try {
      userid = 0;
      while (rs.next()) {
        userid = rs.getInt(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return userid;
  }

}
