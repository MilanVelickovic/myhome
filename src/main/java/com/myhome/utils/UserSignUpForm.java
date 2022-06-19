package com.myhome.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpForm {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private String repeatPassword;

    public UserSignUpForm(String firstName, String lastName, String phoneNumber, String emailAddress, String password, String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public UserSignUpForm() {

    }
}
