package dtu.project.log4jboot2root;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class AppUser { //Represents a record in the APP_USER table in DB
    private Long userID;
    private String username;
    private String password;
    private String email;

    private List<GrantedAuthority> authorityList;

    public AppUser(){}

    public AppUser(Long userID, String username, String email,String password) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public AppUser(String username, String password, List<GrantedAuthority> authorityList){
        //used in UserDetailsServiceImpl
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
