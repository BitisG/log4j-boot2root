package dtu.project.log4jboot2root;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserDAO userDAO = new UserDAO();

    public Object[] getUser(String username) {
        Object list[] = null;
        try {
            list = userDAO.findUsers(username);
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT IN APPUSERSERVICE: getUser():");
            System.out.println(e.getMessage());
        }
        return list;
    }

    public String getUsername(Object principal) {
        User user = (User) principal;
        return user.getUsername();
    }
}
