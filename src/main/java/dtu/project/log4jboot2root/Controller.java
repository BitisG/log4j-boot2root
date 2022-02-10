package dtu.project.log4jboot2root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@org.springframework.stereotype.Controller
public class Controller {
    ResourceLoader loader;
    private static final Logger logger = LogManager.getLogger();

    @GetMapping("/hello")
    public String index() {
        return ("helloWorld");
    }

    public String robots(){
        Resource resource = loader.getResource("classpath:static/robots.txt");
        return asString(resource);
    }

    @GetMapping("/16144cf950518a312e26b9827d91449d166ed6e38e7cc569a9f3c559")
    public String ticketForm(Model model){
        model.addAttribute("ticket", new Ticket());
        return "ticket";
    }

    @PostMapping("/16144cf950518a312e26b9827d91449d166ed6e38e7cc569a9f3c559")
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
