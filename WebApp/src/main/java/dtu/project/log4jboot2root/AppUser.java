package dtu.project.log4jboot2root;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUser implements UserDetails { //Represents a record in the APP_USER table in DB

    private static final long serialVersionUID = 1L;

    private Long userID;
    private String username;
    private String password;
    private String email;

    private List<GrantedAuthority> authorities=null;

    public AppUser(){}

    public AppUser(Long userID, String username, String email,String password) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
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




    /*
    In an actual application, these would have to actually do something of course, however for this project where accounts
    don't need to have the ability to be locked, expired and so on, they just return true by default.
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
