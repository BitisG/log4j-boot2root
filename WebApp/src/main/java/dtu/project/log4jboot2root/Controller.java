package dtu.project.log4jboot2root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    ResourceLoader loader;
    private static final Logger logger = LogManager.getLogger();

    //is used to get userdetails of currently logged in user
    //private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public Controller(TicketService ticketService, AppUserService appUserService) {
        this.ticketService = ticketService;
        this.appUserService = appUserService;
    }

    @GetMapping("/tickets")
    public String tickets(Map<String, Object> model) {
        model.put("tickets", ticketService.getActiveTickets());
        return "tickets";
    }

    @GetMapping("/supporters")
    public String supporters() {
        return "supporters";
    }

    @PostMapping("/searchUsers")
    public String searchUsers(@RequestParam("name") String name, Map<String, Object> model) {
        Object list[] = appUserService.getUser(name);
        
        model.put("users", list[0]);
        if (list[1] != null) {
            model.put("error", list[1]);
        }

        return "supporters";
    }

    @PostMapping("/addTicket")
    public String addTicket(@RequestParam("description") String description) {
        String currentUserName = "anon";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null) {
            System.out.println("Authentication Null");
        }
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            AppUser user = (AppUser) authentication.getPrincipal();
            currentUserName = user.getUsername();
        }
        ticketService.addTicket(currentUserName, description);
        logger.warn("[+] ticket created with content: " + description);
        return "redirect:/tickets";
    }

    @PostMapping("/deleteTicket")
    public String deleteTicket(@RequestParam String id) {
        ticketService.deleteTicket((id));
        return "redirect:/tickets";
    }

    @GetMapping(value = {"/"})
    public String index(Model model) {
        return "WelcomePage";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "login");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("title", "logout");
        return "logout";
    }

    @GetMapping("/denied")
    public String denied(Model model) {
        model.addAttribute("message", "You do not have access to this page!");
        return "denied";
    }

    public String robots() {
        Resource resource = loader.getResource("classpath:static/robots.txt");
        return asString(resource);
    }

    @GetMapping("/44a86b4e2c89f87be46c3ad9f24128dc")
    public String secret() {
        return "secret";
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
