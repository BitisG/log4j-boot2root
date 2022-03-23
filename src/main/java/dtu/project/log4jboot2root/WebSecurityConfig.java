package dtu.project.log4jboot2root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //These pages do not require auth
        http.authorizeRequests().antMatchers("/","/login","/logout").permitAll();

        //Requires user or admin
        http.authorizeRequests().antMatchers("/internal").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

<<<<<<< Updated upstream
        http.authorizeRequests().antMatchers("/createTicket","/admin").access("hasRole('ROLE_ADMIN')");
=======
        http.authorizeRequests().antMatchers("/create_ticket","/admin","/tickets").access("hasRole('ROLE_ADMIN')");
>>>>>>> Stashed changes

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/denied");

        http.authorizeRequests().and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/internal")
<<<<<<< Updated upstream
                .failureUrl("/login?err=true")
=======
                .failureUrl("/login?error=true")
>>>>>>> Stashed changes
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/loggedOutSuccess");
    }
}
