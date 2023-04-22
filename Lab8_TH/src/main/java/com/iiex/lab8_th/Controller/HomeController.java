package com.iiex.lab8_th.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/contact")
    public String Contact(Model model){
        String name = "";
        model.addAttribute("name",name);
        return "contact";
    }
    @PostMapping("/contact")
    public String submit(@ModelAttribute("name") String name, Model model){
//        model.addAttribute("name",name);
        System.out.println(name);
        return  "index";
    }
    @GetMapping("/about")
    @ResponseBody
    public String About(){
        return "About this site";
    }

}