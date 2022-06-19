package com.myhome.services.user;

import com.myhome.models.User;
import com.myhome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty())
            return null;

        return user.get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty())
            return null;

        return user.map(UserDetailsImpl::new).get();
    }
}
