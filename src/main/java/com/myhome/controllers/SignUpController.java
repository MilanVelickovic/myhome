package com.myhome.controllers;

import com.myhome.models.User;
import com.myhome.services.role.RoleService;
import com.myhome.services.user.UserService;
import com.myhome.utils.HashCreator;
import com.myhome.utils.UserSignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Paths;
import java.time.Instant;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public ModelAndView getSignUpPage() {

        UserSignUpForm userSignUpForm = new UserSignUpForm();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userSignUpForm", userSignUpForm);
        modelAndView.addObject("emailError", false);
        modelAndView.addObject("passwordError", false);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView signUp(@Validated UserSignUpForm userSignUpForm, BindingResult bindingResult) {

        User user = userService.findByEmail(userSignUpForm.getEmailAddress());
        HashCreator hashCreator = new HashCreator();

        ModelAndView modelAndView = new ModelAndView();

        if (user == null) {
            if (userSignUpForm.getPassword().equals(userSignUpForm.getRepeatPassword())) {
                user = new User();
                user.setId(null);
                user.setEmail(userSignUpForm.getEmailAddress());
                user.setFirstName(userSignUpForm.getFirstName());
                user.setLastName(userSignUpForm.getLastName());
                user.setPhoneNumber(userSignUpForm.getPhoneNumber());
                user.setPassword(bCryptPasswordEncoder.encode(userSignUpForm.getPassword()));
                user.setRole(roleService.findById(1));
                user.setAvatar("ava1");
                user.setJoinedOn(Instant.now());

                userService.save(user);

                try {
                    String path = Paths.get(".").toAbsolutePath().toString();
                    path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/" + hashCreator.createHash(user.getEmail());

                    File file = new File(path);

                    if (file.mkdir()) {
                        System.out.println("User directory created successfully!");
                    } else {
                        System.out.println("Failed to create user directory!");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                modelAndView.setViewName("signin");

            } else {
                modelAndView.setViewName("signup");
                modelAndView.addObject("passwordError", true);
            }
        } else {
            modelAndView.setViewName("signup");
            modelAndView.addObject("emailError", true);
        }

        return modelAndView;
    }
}
