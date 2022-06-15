package com.myhome.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/signin")
public class SignInController {

    @GetMapping
    public ModelAndView getSignInPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        return modelAndView;
    }

}
