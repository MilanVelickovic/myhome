package com.myhome.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/signout")
public class SignOutController {

    @GetMapping
    public ModelAndView getSignOutPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signout");
        return modelAndView;
    }

}
