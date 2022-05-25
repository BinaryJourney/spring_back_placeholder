package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.User;
import isep.webtechno.placeholder.exceptions.UsersNotFoundException;
import isep.webtechno.placeholder.forms.MaisonForm;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
public class BaseController {

    @Autowired
    MaisonsRepository maisonsRepository;

    @Autowired
    UserRepository userRepository;

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
    public String houseForm(Model model, MaisonForm maison) {
        model.addAttribute("maison", maison);
        model.addAttribute("user-id", Objects.requireNonNull(this.getLoggedUserProvider()).getId());
        return "houseform";
    }

    @PostMapping("/houseform")
    public String houseFormSubmit(@Valid @ModelAttribute("maison") MaisonForm maison, BindingResult bindingResult, Model model) {
        model.addAttribute("maison", maison);
        
        if(bindingResult.hasErrors()) {
            return "houseform";
        }

        User user = getUserFromUserProvider(Objects.requireNonNull(getLoggedUserProvider()));
        Maisons maison_entity = new Maisons(maison.getTitre(), maison.getDescription(), maison.getListeServices(),
                maison.getDateDispoDebut(), maison.getDateDispoFin(), user);

        //Rentre la maison en base
        maisonsRepository.save(maison_entity);

        return "house";
    }

}
