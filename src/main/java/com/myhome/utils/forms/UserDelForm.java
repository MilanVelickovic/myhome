package com.myhome.utils.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDelForm {

    private Integer id;
    private String confirmationCode;

    public UserDelForm(Integer id, String confirmationCode) {
        this.id = id;
        this.confirmationCode = confirmationCode;
    }

    public UserDelForm() {

    }

}
