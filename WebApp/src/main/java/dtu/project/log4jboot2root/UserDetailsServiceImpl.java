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
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDAO.findSingleUser(username);

        if (user == null) {
            String err = "user with name: " + username + " not found";
            throw new UsernameNotFoundException(err);
        }

        List<String> roles = this.roleDAO.getRoleName(user.getUserID());
        List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (String role: roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grants.add(authority);
            }
        }
        user.setAuthorities(grants);

        //return (UserDetails) new AppUser(appUser.getUsername(), appUser.getPassword(), grants);
        return user;
    }
}
