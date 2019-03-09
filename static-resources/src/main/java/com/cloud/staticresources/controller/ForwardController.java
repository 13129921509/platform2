package com.cloud.staticresources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/forward/{str}")
    public String forward(@PathVariable String str){
        return str;
    }

}
