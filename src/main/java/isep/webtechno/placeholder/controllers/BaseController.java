package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.repositories.MaisonsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    @Autowired
    MaisonsRepository maisonsRepository;

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping("/home")
    public ModelAndView homeMapping () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping("/homelist")
    public ModelAndView maisonMapping () {
        ModelAndView modelAndView = new ModelAndView("house/list");
        modelAndView.addObject("houses", maisonsRepository.findAll());
//        logger.info(maisonsRepository.findAll().get(0).toString());
        modelAndView.setViewName("homelist");
        return modelAndView;
    }

    @RequestMapping("/userlist")
    public ModelAndView userMapping () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

}
