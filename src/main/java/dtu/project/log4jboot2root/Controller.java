package dtu.project.log4jboot2root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import static java.nio.charset.StandardCharsets.UTF_8;



@org.springframework.stereotype.Controller
public class Controller {

    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://database:3306/app");
        dataSourceBuilder.username("peter");
        dataSourceBuilder.password("strongpassword");
        System.out.println("Building dataSource");
        return dataSourceBuilder.build();
    }


    JdbcTemplate SQLDataLoader = new JdbcTemplate(getDataSource());
    ResourceLoader loader;
    private static final Logger logger = LogManager.getLogger();

    @GetMapping(value = {"/hello", "/"})
    public String index(Model model) {
        return ("WelcomePage");
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("title", "admin page");
        return "adminPage";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "login");
        return "login";
    }

    @GetMapping
    public String logout(Model model) {
        model.addAttribute("title", "logout");
        return "logout";
    }

    /* @PostMapping("/login")
    public RedirectView loggingIn(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);

        String sql = "SELECT USER_ID, ENCRYPTED_PASSWORD FROM APP_USER WHERE USER_NAME = \"" + user.getUsername() + "\"";

        List<User> r = SQLDataLoader.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                return user;
            }
        });

        if (r.isEmpty())
            return new RedirectView("/hello");
        else {
            if (Base64.getEncoder().encodeToString(user.getPassword().getBytes()).equals(r.get(0).getPassword()))
                return new RedirectView("/16144cf950518a312e26b9827d91449d166ed6e38e7cc569a9f3c559");
            else
                return new RedirectView("/hello");
        }
    } */

    @GetMapping("/denied")
    public String denied(Model model, User user) {
        if (user.getUsername() != null) {
            model.addAttribute("message", "Hi " + user.getUsername() + "you do not have access to this page");
        } else {
            model.addAttribute("message", "You do not have access to this page!");
        }
        return "denied";
    }

    public String robots(){
        Resource resource = loader.getResource("classpath:static/robots.txt");
        return asString(resource);
    }

    @GetMapping("/create_ticket")
    public String ticketForm(Model model){
        model.addAttribute("ticket", new Ticket());
        return "ticket";
    }

    @PostMapping("/create_ticket")
    public String ticketReceive(@ModelAttribute Ticket ticket, Model model){
        model.addAttribute("ticket", ticket);
        logger.warn("[+] ticket id: " + ticket.getId() + " Content: " + ticket.getContent());
        return "ticketResult";
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
