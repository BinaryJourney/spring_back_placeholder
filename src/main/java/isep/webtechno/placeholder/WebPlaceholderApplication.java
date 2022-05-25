package isep.webtechno.placeholder;

import isep.webtechno.placeholder.controllers.FileUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class WebPlaceholderApplication {

    public static void main(String[] args) {
        new File(FileUploadController.uploadDirectory).mkdir();
        SpringApplication.run(WebPlaceholderApplication.class, args);
    }
}
