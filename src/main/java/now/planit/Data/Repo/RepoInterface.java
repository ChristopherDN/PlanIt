package now.planit.Data.Repo;


import java.sql.ResultSet;

public interface RepoInterface {

    void query(String sql);
    ResultSet load(String sql);
    Object results(ResultSet resultSet);
}
