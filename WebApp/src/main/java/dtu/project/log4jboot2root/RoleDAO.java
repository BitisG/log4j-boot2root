package dtu.project.log4jboot2root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class RoleDAO extends JdbcDaoSupport {

    @Autowired
    public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<String> getRoleName(Long userID) {
        String sql = "select r.ROLE_NAME from USER_ROLE ur, APP_ROLE r where ur.ROLE_ID = r.ROLE_ID and ur.USER_ID = ? ";

        Object[] list = new Object[] {userID};

        List<String> roles = this.getJdbcTemplate().queryForList(sql, list, String.class);

        return roles;

    }

}
