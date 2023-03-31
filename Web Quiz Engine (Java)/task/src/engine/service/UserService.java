package engine.service;

import engine.entity.User;
import engine.model.response.exceptions.DuplicateEmailException;
import engine.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("No user %s found", username)));
    }

    public void registerNewUser(String username, String password) {

        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new DuplicateEmailException(HttpStatus.BAD_REQUEST);
        }

        String encodePassword = encoder.encode(password);
        User user = new User(username, encodePassword);

        userRepository.save(user);
    }
}
