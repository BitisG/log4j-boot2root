package dtu.project.log4jboot2root;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppUserDAO {

    public List<AppUser> findUsers(String username) {
        List<AppUser> userList = new ArrayList<AppUser>();
        String query = String.format("SELECT USER_ID, USER_NAME, EMAIL FROM APP_USER WHERE USER_NAME LIKE "
                + "'%s'", username);
        Connector connector = new Connector();
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

}
