package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Images;
import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.User;
import isep.webtechno.placeholder.exceptions.UsersNotFoundException;
import isep.webtechno.placeholder.forms.MaisonForm;
import isep.webtechno.placeholder.repositories.ImagesRepository;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
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
    public String houseForm(Model model, Maisons maison/*, MultipartFile[] files*/) {
        model.addAttribute("maison", maison);
//        model.addAttribute("files", files);
        return "houseform";
    }

    @PostMapping("/houseform")
    public String houseFormSubmit(@Valid @ModelAttribute("maison") Maisons maison,
                                  BindingResult bindingResult, Model model/*, @RequestParam(value = "files") MultipartFile[] files*/) /*throws IOException*/ {
        model.addAttribute("maison", maison);
//        model.addAttribute("files", files);

        if(bindingResult.hasErrors()) {
            return "houseform";
        }

        User user = getUserFromUserProvider(Objects.requireNonNull(getLoggedUserProvider()));
        maison.setUser(user);

        //Rentre la maison en base
        maison = maisonsRepository.save(maison);

//        for (int i = 0; i < Arrays.stream(files).count(); i++) {
//            files[i].transferTo(Paths.get("/images/"));
//            imagesRepository.save(new Images(files[i].getOriginalFilename(), maison);
//        }

        return "house";
    }

}
