package com.myhome.controllers;

import com.myhome.models.User;
import com.myhome.services.advertisement.AdvertisementService;
import com.myhome.services.user.UserDetailsImpl;
import com.myhome.services.user.UserService;
import com.myhome.utils.Converter;
import com.myhome.utils.Countries;
import com.myhome.utils.HashCreator;
import com.myhome.utils.forms.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final AdvertisementService advertisementService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/profile/edit")
    public ModelAndView getEditPage() {

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.setViewName("profileedit");
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage() {

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        String country = new Countries().getCountryNameByDialCodeFromJSON(user.getPhoneNumber().substring(0, user.getPhoneNumber().indexOf(' ')));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("country", country);
        modelAndView.addObject("advertisements", advertisementService.findByUser(user));
        modelAndView.addObject("converter", new Converter());
        modelAndView.addObject("hashCreator", new HashCreator());
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @PostMapping("/profile/delete")
    public ModelAndView getProfileDeletePage() {

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        System.out.println(user.getEmail());
        userService.delete(user);

        ModelAndView modelAndView = new ModelAndView("redirect:/signout");
        return modelAndView;
    }

    @PostMapping("/profile/update")
    public ModelAndView updateProfile(@Validated UserForm userForm) {

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        ModelAndView modelAndView = new ModelAndView("redirect:/profile/edit");

        if (!user.getEmail().equals(userForm.getEmailAddress())) {

            HashCreator hashCreator = new HashCreator();

            try {
                String path = Paths.get(".").toAbsolutePath().toString();
                path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/gallery/" + hashCreator.createHash(user.getEmail());
                String newPath = path.substring(0, path.lastIndexOf('/')) + "/" + hashCreator.createHash(userForm.getEmailAddress());

                if (new File(path).renameTo(new File(newPath))) {
                    System.out.println("User directory renamed successfully!");
                } else {
                    System.out.println("Failed to rename user directory!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (userForm.getPassword().equals(userForm.getRepeatPassword())) {

            user.setAvatar(userForm.getAvatar());
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setEmail(userForm.getEmailAddress());

            if (userForm.getPassword().equals(user.getPassword())) {
                user.setPassword(userForm.getPassword());
            } else {
                user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
            }

            userService.update(user);
            modelAndView.addObject("editPerformed", true);

        } else {
            modelAndView.addObject("editFailed", true);
        }

        return modelAndView;
    }

}
