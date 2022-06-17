package com.myhome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signout")
public class SignOutController {

    @GetMapping
    public ModelAndView getSignOutPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signout");
        return modelAndView;
    }

}
