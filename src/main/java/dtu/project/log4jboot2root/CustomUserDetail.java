package dtu.project.log4jboot2root;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomUserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;
    private AppUser appuser;

    List<GrantedAuthority> authorities=null;

    public AppUser getUser() {
        return appuser;
    }

    public void setUser(AppUser appuser) {
        this.appuser = appuser;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities)
    {
        this.authorities=authorities;
    }

    public String getPassword() {
        return appuser.getPassword();
    }

    public String getUsername() {
        return appuser.getUsername();
    }

    public boolean isAccountNonExpired() {
        return appuser.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        return appuser.isAccountNonLocked();
    }

    public boolean isCredentialsNonExpired() {
        return appuser.isCredentialsNonExpired();
    }

    public boolean isEnabled() {
        return appuser.isAccountEnabled();
    }

}