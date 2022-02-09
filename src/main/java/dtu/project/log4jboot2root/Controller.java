package dtu.project.log4jboot2root;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
public class Controller {
    ResourceLoader loader;

    @GetMapping("/hello")
    public String index() {
        return ("Hello World!");
    }
    @RequestMapping("/test")
    public String robots(){
        Resource resource = loader.getResource("classpath:static/robots.txt");
        return asString(resource);
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
