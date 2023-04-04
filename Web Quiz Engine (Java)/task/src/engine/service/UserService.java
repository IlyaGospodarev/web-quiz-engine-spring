package engine.service;

import engine.entity.User;
import engine.exceptions.InvalidEmailException;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public void saveUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            User userNew = new User();
            userNew.setEmail(email);
            userNew.setPassword(bCryptPasswordEncoder.encode(password));

            userRepository.save(userNew);
        } else {
            throw new InvalidEmailException();
        }
    }
}

