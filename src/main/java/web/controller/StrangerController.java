package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StrangerController {

    @GetMapping("/stranger")
    public String printBye(ModelMap model) {
        return "stranger";
    }
}
