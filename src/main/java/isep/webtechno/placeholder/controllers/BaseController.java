package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
import isep.webtechno.placeholder.security.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class BaseController {

    @Autowired
    MaisonsRepository maisonsRepository;

    private UserProvider getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserProvider user = (UserProvider)authentication.getPrincipal();
            return user;
        }
        return null;
    }

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/home")
    public String homeMapping(Model model) {
        model.addAttribute("metaTitle", "Acceuil - SwapHome");
        return "home";
    }

    @RequestMapping("/houselist")
    public ModelAndView maisonMapping () {
        ModelAndView modelAndView = new ModelAndView("house/list");
        modelAndView.addObject("houses", maisonsRepository.findAll());
//        logger.info(maisonsRepository.findAll().get(0).toString());
        modelAndView.setViewName("houselist");
        return modelAndView;
    }

    @RequestMapping("/userlist")
    public ModelAndView userMapping () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @GetMapping("/houseform")
    public String houseForm(Model model) {
        model.addAttribute("maison", new Maisons());
        model.addAttribute("user-id", Objects.requireNonNull(this.getLoggedUser()).getId());
        return "houseform";
    }

    @PostMapping("/houseform")
    public String houseFormSubmit(@ModelAttribute Maisons maison, Model model) {
        model.addAttribute("maison", maison);
        return "house";
    }

}
