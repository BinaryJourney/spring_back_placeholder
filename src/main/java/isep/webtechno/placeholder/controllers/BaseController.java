package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Images;
import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.Tags;
import isep.webtechno.placeholder.entities.User;
import isep.webtechno.placeholder.exceptions.UsersNotFoundException;
import isep.webtechno.placeholder.forms.MaisonForm;
import isep.webtechno.placeholder.repositories.ImagesRepository;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
import isep.webtechno.placeholder.repositories.TagsRepository;
import isep.webtechno.placeholder.repositories.UserRepository;
import isep.webtechno.placeholder.security.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class BaseController {

    @Autowired
    MaisonsRepository maisonsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImagesRepository imagesRepository;

    @Autowired
    TagsRepository tagsRepository;

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    private UserProvider getLoggedUserProvider() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserProvider user = (UserProvider)authentication.getPrincipal();
            return user;
        }
        return null;
    }

    private User getUserFromUserProvider(UserProvider userProvider) {
        return userRepository.findById(userProvider.getId()).orElseThrow(()
                -> new UsersNotFoundException(userProvider.getId()));
    }


    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/home")
    public String homePageMapping(Model model) {
        model.addAttribute("metaTitle", "SwapHome - Acceuil");
        return "home";
    }

    @GetMapping("/CGU")
    public String CGUMapping(Model model) {
        model.addAttribute("metaTitle", "SwapHome - CGU");
        return "CGU";
    }

    @GetMapping("/userlist")
    public String userlistMapping(Model model) {
        model.addAttribute("metaTitle", "SwapHome - Liste des utilisateurs");
        return "userlist";
    }

    @RequestMapping("/houselist")
    public ModelAndView maisonMapping () {
        ModelAndView modelAndView = new ModelAndView("house/list");
        modelAndView.addObject("houses", maisonsRepository.findAll());
//        logger.info(maisonsRepository.findAll().get(0).toString());
        modelAndView.setViewName("houselist");
        return modelAndView;
    }

//    @RequestMapping("/userlist")
//    public ModelAndView userMapping () {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("userlist");
//        return modelAndView;
//    }

    @GetMapping("/houseform")
    public String houseForm(Model model, Maisons maison) {
        model.addAttribute("maison", maison);
        List<Tags> tagsList = tagsRepository.findAll();
        model.addAttribute("tags", tagsList);
        return "houseform";
    }

    @PostMapping("/houseform")
    public String houseFormSubmit(@Valid @ModelAttribute("maison") Maisons maison,
                                  BindingResult bindingResult, Model model, @RequestParam(value = "files") MultipartFile[] files) throws IOException {
        model.addAttribute("maison", maison);
        List<Tags> tagsList = tagsRepository.findAll();
        model.addAttribute("tags", tagsList);

        if(bindingResult.hasErrors()) {
            return "houseform";
        }

        User user = getUserFromUserProvider(Objects.requireNonNull(getLoggedUserProvider()));
        maison.setUser(user);

        /** Rentre la maison en base **/
        maison = maisonsRepository.save(maison);

        StringBuilder fileNames = new StringBuilder();
        List<Images> imagesList = new ArrayList<>();
        String randomUUID = UUID.randomUUID().toString();
        Images temp_image;
        for(MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, randomUUID + file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()+" ");
            Files.write(fileNameAndPath, file.getBytes());
            temp_image = new Images(randomUUID + file.getOriginalFilename(), maison);
            imagesList.add(imagesRepository.save(temp_image));
            maison.addImages(temp_image);
        }
        logger.info(maison.toString());
        model.addAttribute("uploadedFiles", imagesList);

        return "house";
    }

}
