package dtu.project.log4jboot2root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {
    //This class is used to manipulate the APP_USER table. Contains methods to for example find user based on name

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public AppUser findUserAccount(String username) {
        //Select .. from APP_USER u where u.USER_NAME = ?
        String sql = AppUserMapper.BASE_SQL + "where u.USER_NAME = " + username;

        Object[] params = new Object[] {username};
        AppUserMapper mapper = new AppUserMapper();
        try {
            AppUser appUser = this.getJdbcTemplate().queryForObject(sql, mapper);
            //AppUser appUser = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return appUser;
        } catch (Exception e) {
            return null;
        }

    }

}
