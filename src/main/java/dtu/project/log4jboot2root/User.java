package dtu.project.log4jboot2root;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<GrantedAuthority> auth;

    public User(){}

    public User(String username, String password, List<GrantedAuthority> auth) {
        this.username = username;
        this.password = password;
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
