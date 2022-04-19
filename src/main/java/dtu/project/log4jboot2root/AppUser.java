package dtu.project.log4jboot2root;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class AppUser { //Represents a record in the APP_USER table in DB
    private Long userID;
    private String username;
    private String password;

    private List<GrantedAuthority> authorityList;

    public AppUser(){}

    public AppUser(Long userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password, List<GrantedAuthority> authorityList){
        this.username = username;
        this.password = password;
        this.authorityList = authorityList;
    }

    public Long getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
