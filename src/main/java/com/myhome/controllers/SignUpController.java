package com.myhome.controllers;

import com.myhome.models.User;
import com.myhome.services.role.RoleService;
import com.myhome.services.user.UserService;
import com.myhome.utils.HashCreator;
import com.myhome.utils.forms.UserForm;
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

        UserForm userForm = new UserForm();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", userForm);
        modelAndView.addObject("emailError", false);
        modelAndView.addObject("passwordError", false);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView signUp(@Validated UserForm userForm, BindingResult bindingResult) {

        User user = userService.findByEmail(userForm.getEmailAddress());
        HashCreator hashCreator = new HashCreator();

        ModelAndView modelAndView = new ModelAndView();

        if (user == null) {
            if (userForm.getPassword().equals(userForm.getRepeatPassword())) {
                user = new User();
                user.setId(null);
                user.setEmail(userForm.getEmailAddress());
                user.setFirstName(userForm.getFirstName());
                user.setLastName(userForm.getLastName());
                user.setPhoneNumber(userForm.getPhoneNumber());
                user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
                user.setRole(roleService.findById(1));
                user.setAvatar("ava1");
                user.setJoinedOn(Instant.now());

                userService.save(user);

                try {
                    String path = Paths.get(".").toAbsolutePath().toString();
                    path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/gallery/" + hashCreator.createHash(user.getEmail());

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
