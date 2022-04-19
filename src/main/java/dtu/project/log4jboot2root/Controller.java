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

    ResourceLoader loader;
    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", "Welcome to LogCorp. We love to log!");
        return "WelcomePage";
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
