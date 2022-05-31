package dtu.project.log4jboot2root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppUserDAO {
    Connector connector = new Connector();
    Connection conn = connector.getConnection();

    public List<AppUser> findUsers(String username) {
        List<AppUser> userList = new ArrayList<AppUser>();
        String query = String.format("SELECT USER_ID, USER_NAME, EMAIL FROM APP_USER WHERE USER_NAME LIKE "
                + "'%s'", username);
        Connection conn = connector.getConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                AppUser appUser = new AppUser();
                appUser.setUserID(Long.valueOf(resultSet.getString(1)));
                appUser.setUsername(resultSet.getString(2));
                appUser.setEmail(resultSet.getString(3));
                userList.add(appUser);
            }

            statement.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT:");
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public AppUser findUser(String username) {
        AppUser user = new AppUser();
        String query = "SELECT USER_ID, USER_NAME, EMAIL, PASSWORD FROM APP_USER WHERE USER_NAME = ?";
        Connection conn = connector.getConnection();
        
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            user.setUserID(Long.valueOf(resultSet.getString(1)));
            user.setUsername(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));

            statement.close();
            
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT:");
            System.out.println(e.getMessage());
        }
        return user;
    }

}
