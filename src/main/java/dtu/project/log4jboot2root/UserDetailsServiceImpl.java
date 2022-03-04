package dtu.project.log4jboot2root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private AppRoleDAO appRoleDAO;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.appUserDAO.findUserAccount(username);

        if (appUser == null) {
            String err = "user with name: " + username + " not found";
            throw new UsernameNotFoundException(err);
        }

        List<String> roles = this.appRoleDAO.getRoleName(appUser.getUserID());

        List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (String role: roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grants.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(), appUser.getEncryptedPassword(), grants);

        return userDetails;
    }
}
