package index.app;

import index.service.IndexService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "index")
@RestController
public class DemoApplication {

    private final IndexService indexService;

    public DemoApplication(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping({"/", "/index"})
    public String home() {
        return indexService.message();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}