package com.myhome.services.user;

import com.myhome.models.User;
import com.myhome.repository.UserRepository;
import com.myhome.utils.HashCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
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
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {

        System.out.println(user.getEmail());

        HashCreator hashCreator = new HashCreator();

        try {
            String path = Paths.get(".").toAbsolutePath().toString();
            path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/gallery/" + hashCreator.createHash(user.getEmail());

            System.out.println(path);

            if (new File(path).delete()) {
                System.out.println("User directory deleted successfully!");
            } else {
                System.out.println("Failed to delete user directory!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        userRepository.deleteById(user.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty())
            return null;

        return user.map(UserDetailsImpl::new).get();
    }
}
