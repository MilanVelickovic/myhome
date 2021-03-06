package com.myhome.services.user;

import com.myhome.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByEmail(String email);

    public User save(User user);

    public User update(User user);

    public void delete(User user);

    public User findById(Integer id);

}
