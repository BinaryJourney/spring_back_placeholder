package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.*;
import groovy.transform.Undefined;
import isep.webtechno.placeholder.exceptions.UsersNotFoundException;
import isep.webtechno.placeholder.repositories.*;
import isep.webtechno.placeholder.security.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    ReservationsRepository reservationsRepository;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/userlist")
    public String userlistMapping(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("metaTitle", "SwapHome - Liste des utilisateurs");
        model.addAttribute("users", userList);
        return "userlist";
    }

    @GetMapping("/user/{id}")
    public String userMapping(Model model, @PathVariable Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("Pas d'utilisateur avec l'id " + id));
        List<Maisons> maisonsList = maisonsRepository.findByUser(user);
        List<Reservations> reservationsList = reservationsRepository.findByUser(user);

        model.addAttribute("maisons", maisonsList);
        model.addAttribute("reservations", reservationsList);
        model.addAttribute("user", user);
        logger.info(user.toString());
        return "user";
    }

    @RequestMapping("/houselist")
    public ModelAndView maisonMapping () {
        ModelAndView modelAndView = new ModelAndView("house/list");
        modelAndView.addObject("houses", maisonsRepository.findAll());
        modelAndView.setViewName("houselist");
        return modelAndView;
    }

    @GetMapping("/houseform")
    public String houseForm(Model model, Maisons maison) {
        model.addAttribute("maison", maison);

        List<List<Tags>> typeTagsList = new ArrayList<>();
        typeTagsList.add(tagsRepository.findAllByType("Salle de bain"));
        typeTagsList.add(tagsRepository.findAllByType("Chambre et linge"));
        typeTagsList.add(tagsRepository.findAllByType("Divertissement"));
        typeTagsList.add(tagsRepository.findAllByType("Famille"));
        typeTagsList.add(tagsRepository.findAllByType("Chauffage et climatisation"));
        model.addAttribute("tagTypesList", typeTagsList);

        return "houseform";
    }

    @PostMapping("/houseform")
    public String houseFormSubmit(@Valid @ModelAttribute("maison") Maisons maison,
                                  BindingResult bindingResult, Model model, @RequestParam(value = "files") MultipartFile[] files) throws IOException {
        model.addAttribute("maison", maison);

        List<List<Tags>> typeTagsList = new ArrayList<>();
        typeTagsList.add(tagsRepository.findAllByType("Salle de bain"));
        typeTagsList.add(tagsRepository.findAllByType("Chambre et linge"));
        typeTagsList.add(tagsRepository.findAllByType("Divertissement"));
        typeTagsList.add(tagsRepository.findAllByType("Famille"));
        typeTagsList.add(tagsRepository.findAllByType("Chauffage et climatisation"));
        model.addAttribute("tagTypesList", typeTagsList);

        if(bindingResult.hasErrors()) {
            return "houseform";
        }

        User user = getUserFromUserProvider(Objects.requireNonNull(getLoggedUserProvider()));
        maison.setUser(user);

        /** Rentre la maison en base **/
        maisonsRepository.save(maison);

        StringBuilder fileNames = new StringBuilder();
        List<Images> imagesList = new ArrayList<>();
        String randomUUID = UUID.randomUUID().toString();
        Images temp_image;
        for(MultipartFile file : files) {
            if(file.getOriginalFilename().equals("")) break;
            Path fileNameAndPath = Paths.get(uploadDirectory, randomUUID + file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()+" ");
            Files.write(fileNameAndPath, file.getBytes());
            temp_image = new Images(randomUUID + file.getOriginalFilename(), maison);
            imagesList.add(imagesRepository.save(temp_image));
            maison.addImages(temp_image);
        }
        for(Tags tag : maison.getTags()) {

        }
        logger.info(maison.toString());
        model.addAttribute("uploadedFiles", imagesList);

        return "house";
    }

    @GetMapping("/house/{id}")
    public String getHouse(@PathVariable Long id, Model model) throws Exception {
        Maisons maison = maisonsRepository.findById(id).orElseThrow(() -> new Exception("No house with id " + id));
        model.addAttribute("maison", maison);
        logger.info(maison.toString());
        return "house";
    }

    @GetMapping("/housereservation/{id}")
    public String houseReservation(@PathVariable String id, Model model, Reservations reservations) throws Exception {
        Maisons maison = maisonsRepository.findById(Long.valueOf(id)).orElseThrow(() -> new Exception("No house with id " + id));
        model.addAttribute("reservations",reservations);
        model.addAttribute("routeId", id);
        return "housereservation";
    }

    @PostMapping("/housereservation/{id}")
    public String houseReservationSubmit(@Valid @ModelAttribute("reservations") Reservations reservations,
                                         BindingResult bindingResult, Model model, RedirectAttributes attribute,
                                         @PathVariable String id) throws Exception {
        model.addAttribute("reservations",reservations);
        model.addAttribute("routeId", id);

        if(bindingResult.hasErrors()) {
            return "housereservation";
        }

        Maisons maison = maisonsRepository.findById(Long.valueOf(id)).orElseThrow(() -> new Exception("No house with id " + id));
        User user = getUserFromUserProvider(Objects.requireNonNull(getLoggedUserProvider()));
        reservations.setUser(user);
        reservations.setMaison(maison);

        reservationsRepository.save(reservations);
        logger.info(reservations.toString());
        model.addAttribute("maison", maison);
        attribute.addFlashAttribute("message","Reservation réussie !");
        return "house";
    }
}
