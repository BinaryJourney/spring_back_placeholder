package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Images;
import isep.webtechno.placeholder.repositories.ImagesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);;

    public static String uploadDirectory = System.getProperty("user.dir")+"/upload";

    @GetMapping("/uploadImage")
    public String uploadPage(Model model)
    {
        logger.info(uploadDirectory);
        return "uploadForm";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("files") MultipartFile[] files, Model model) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        for(MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()+" ");
            Files.write(fileNameAndPath, file.getBytes());
        }
        model.addAttribute("msg", "Successfully uploaded files" + fileNames.toString());
        return "fileUploadView";
    }

}
