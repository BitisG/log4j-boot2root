package dtu.project.log4jboot2root;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<AppUser> {
    //Maps the columns in the APP_USER table based on
    //the fields in the AppUser class
    public static final String BASE_SQL = "Select u.USER_ID, u.USER_NAME, u.ENCRYPTED_PASSWORD From APP_USER u ";

    @Override
    public AppUser mapRow(ResultSet rs, int nowRum) throws SQLException {
        Long userID = rs.getLong("USER_ID");
        String username = rs.getString("USER_NAME");
        String encrypted = rs.getString("ENCRYPTED_PASSWORD");

        return new AppUser(userID, username, encrypted);
    }

}
