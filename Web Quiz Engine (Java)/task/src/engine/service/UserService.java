package engine.service;

import engine.entity.User;
import engine.model.response.exceptions.DuplicateEmailException;
import engine.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Not found: %s", email)));
    }

    public void registerNewUser(String email, String password) {

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new DuplicateEmailException(HttpStatus.BAD_REQUEST);
        }

        String encodePassword = passwordEncoder.encode(password);
        User user = new User(email, encodePassword);

        userRepository.save(user);
    }
}
