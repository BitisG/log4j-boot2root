package dtu.project.log4jboot2root;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    Connector connector = new Connector();
    

    public Object[] findUsers(String username) throws SQLException {

        List<User> userList = new ArrayList<User>();
        String query = String.format("SELECT USER_ID, USER_NAME, EMAIL FROM USER WHERE USER_NAME LIKE "
                + "'%s'", username);
        Connection conn = connector.getConnection();
        Exception error = null;
        try {
            
            Statement statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                User user = new User();
                user.setUserID(Long.valueOf(resultSet.getString(1)));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                userList.add(user);
            }

            statement.close();
            conn.close();

        } catch (Exception e) {
            conn.close();
            error = e;
            System.out.println("EXCEPTION CAUGHT IN APPUSERDAO: findUsers():");
            System.out.println(e.getMessage());
        }
        Object list[] = {userList,error};
        return list;
    }

    public User findSingleUser(String username) {
        User user = new User();
        String query = "SELECT USER_ID, USER_NAME, EMAIL, PASSWORD FROM USER WHERE USER_NAME = ?";
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
            conn.close();
            
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT IN APPUSERDAO: findUsers():");
            System.out.println(e.getMessage());
        }
        return user;
    }

}
