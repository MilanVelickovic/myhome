package com.myhome.utils.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

    private String avatar;
    private String firstName, lastName;
    private String phoneNumber, emailAddress;
    private String password, repeatPassword;

    public UserForm(String avatar, String firstName, String lastName, String phoneNumber, String emailAddress, String password, String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public UserForm() {

    }
}
