package com.ecocleaning.webstore.about;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/sobre")
    public String aboutPage(Model model) {
        return "about/index";
    }
}
