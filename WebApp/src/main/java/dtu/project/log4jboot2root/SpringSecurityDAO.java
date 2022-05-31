package dtu.project.log4jboot2root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;

@Repository
@Transactional
public class SpringSecurityDAO extends JdbcDaoSupport {
    //This class needs to be implemented for Spring Security to work
    AppUserDAO appUserDAO = new AppUserDAO();

    @Autowired
    public SpringSecurityDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    //Used by Spring Security
    public AppUser findUserAccount(String username) {
        return appUserDAO.findUser(username);
    }

}
