package dtu.project.log4jboot2root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport{
    //This class is used to manipulate the APP_USER table. Contains methods to for example find user based on name

    //Used by Spring Security
    public AppUser findUserAccount(String username) {
        //Select .. from APP_USER u where u.USER_NAME = ?
        String sql = AppUserMapper.BASE_SQL + "where u.USER_NAME = ? ";

        Object[] params = new Object[] {username};
        AppUserMapper mapper = new AppUserMapper();
        try {
            return this.getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (Exception e) {
            return null;
        }

    }

    //This class is used to manipulate the APP_USER table. Contains methods to for example find user based on name

    private String url = "jdbc:mysql://172.18.0.2:3306/app";

    public Connection getSqlConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "peter", "strongpassword");
        } catch (Exception e) {
            System.out.println("Exception caught while creating db connection:");
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<User> findUsers(String username) {
        List<User> userList = new ArrayList<User>();
        String query = String.format("SELECT USER_ID, USER_NAME FROM APP_USER WHERE USER_NAME LIKE "
                + "%s", username);
        Connection conn = getSqlConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setID(resultSet.getString(1));
                user.setUsername(resultSet.getString(2));
                userList.add(user);
            }

            statement.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT:");
            System.out.println(e.getMessage());
        }
        return userList;
    }

}
