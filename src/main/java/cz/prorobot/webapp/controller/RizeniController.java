package cz.prorobot.webapp.controller;

import cz.prorobot.webapp.entity.Makra;
import cz.prorobot.webapp.entity.Rizeni;
import cz.prorobot.webapp.entity.RobotDriver;
import cz.prorobot.webapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RizeniController {
    private RizeniRepository repositoryRizeni;
    private MakraRepository repositoryMakra;
    private RobotDriver robotDriver = new RobotDriver();


    public RizeniController(RizeniRepository repositoryRizeni,MakraRepository repositoryMakra) {
        this.repositoryRizeni = repositoryRizeni;
        this.repositoryMakra = repositoryMakra;
    }

    @RequestMapping(value = "/rizeni", method = RequestMethod.GET)
    public ModelAndView zobrazSeznamN() {
        ModelAndView drzakNaData = new ModelAndView("rizeni");
        List<Rizeni> allR = repositoryRizeni.findAll();
        List<Makra> allM = repositoryMakra.findAll();
        drzakNaData.addObject("rizeni", allR);
        drzakNaData.addObject("makra", allM);
        return drzakNaData;
    }



    @RequestMapping(value = "/rizeni/{cislo}", method = RequestMethod.POST)
    public ModelAndView zpracujDetailN(@PathVariable("cislo") Long cislo, Rizeni formular) {

        return new ModelAndView("redirect:/rizeni");
    }




}
