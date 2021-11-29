package now.planit.Data.Repo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface RepoInterface {

  PreparedStatement checkConnection(String SqlCommand);
  PreparedStatement setString(ArrayList<String> parameters);
  void query(String sqlCommand, ArrayList<String> parameters);
  ResultSet load(String sqlCommand, ArrayList<String> parameters);


  //usable??
  void dbInput(String a, String b, String c, String d, String e);


}
