package com.cloud.staticresources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForwardController {
    @PostMapping("/forward/{str}")
    public String forwardLogin(Model model, @PathVariable String str){
        return str;
    }
}