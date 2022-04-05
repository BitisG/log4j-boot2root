package dtu.project.log4jboot2root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;

@org.springframework.stereotype.Controller
public class Controller {
    private final TicketService ticketService;
    private final AppUserService appUserService;

    public Controller(TicketService ticketService, AppUserService appUserService) {
        this.ticketService = ticketService;
        this.appUserService = appUserService;
    }

    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://database:3306/app");
        dataSourceBuilder.username("peter");
        dataSourceBuilder.password("strongpassword");
        System.out.println("Building dataSource");
        return dataSourceBuilder.build();
    }

    @GetMapping("/tickets")
    public String tickets(Map<String, Object> model) {
        model.put("tickets", ticketService.getActiveTickets());
        return "tickets";
    }

    @GetMapping("/supporters")
    public String supporters(Map<String, Object> model) {
        model.put("users", appUserService.getUser("peter"));
        return "supporters";
    }

    @PostMapping("/searchUsers")
    public String searchUsers(@RequestParam("name") String name, Map<String, Object> model) {
        model.put("users", appUserService.getUser(name));
        return "supporters";
    }

    @PostMapping("/addTicket")
    public String addTicket(@RequestParam("description") String description) {
        ticketService.addTicket("admin", description);
        logger.warn("[+] ticket created with content: " + description);
        return "redirect:/tickets";
    }

    @PostMapping("/deleteTicket")
    public String deleteTicket(@RequestParam String id) {
        ticketService.deleteTicket((id));
        return "redirect:/tickets";
    }

    JdbcTemplate SQLDataLoader = new JdbcTemplate(getDataSource());
    ResourceLoader loader;
    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = {"/hello", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", "Welcome to LogCorp. We love to log!");
        return "WelcomePage";
    }

    @GetMapping("/admin")
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

    public String robots() {
        Resource resource = loader.getResource("classpath:static/robots.txt");
        return asString(resource);
    }

    @GetMapping("/44a86b4e2c89f87be46c3ad9f24128dc")
    public String secret(){
        return "secret"; //some html page with the admin pass hidden in the source or something idk
    }

    @GetMapping("/create_ticket")
    public String ticketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket";
    }

    @PostMapping("/create_ticket")
    public String ticketReceive(@ModelAttribute Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        logger.warn("[+] ticket id: " + ticket.getTicketID() + " Content: " + ticket.getDescription());
        return "ticketResult";
    }

    @GetMapping("/loggedOutSuccess")
    public String loggedOut() {
        return "loggedOutSuccess";
    }

    @GetMapping("/internal")
    public String internal() {
        return "internal";
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
