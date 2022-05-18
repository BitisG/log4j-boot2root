package dtu.project.log4jboot2root;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private AppUserDAO appUserDAO = new AppUserDAO();

    public List getUser(String username) {
        return appUserDAO.findUsers(username);
    }

}
