package dtu.project.log4jboot2root;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AppUserService {

    private AppUserDAO appUserDAO = new AppUserDAO();

    public List getUser(String username) {
        List list = null;
        try {
            list = appUserDAO.findUsers(username);
        } catch (SQLException e) {
            System.out.println("EXCEPTION CAUGHT IN APPUSERSERVICE: getUser():");
            System.out.println(e.getMessage());

        }
        return list;
    }


}
