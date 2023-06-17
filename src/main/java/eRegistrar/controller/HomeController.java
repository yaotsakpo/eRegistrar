package eRegistrar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/eRegistrar", "/home", "/eRegistrar/home"})
    public String home() {
        return "home/index";
    }

}
