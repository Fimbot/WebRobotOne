package com.example.webfimbot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String akce1() {
        System.out.println("xxxxxxxxxxxxxxxxx");
        return "Ahoj!!!";
    }

    @RequestMapping("/kolikjehodin")
    @ResponseBody
    public String akce2() {
        return new Date().toString();
    }

    @RequestMapping("/seznam")
    @ResponseBody
    public List<String> akce3() {
        List<String> seznam = new ArrayList<>();
        seznam.add("xxx");
        seznam.add("yyy");
        return seznam;
    }

    @RequestMapping("/r")
    public String akce4() {
        // nejake aplikacni logika
        return "redirect:/kolikjehodin";
    }
}

