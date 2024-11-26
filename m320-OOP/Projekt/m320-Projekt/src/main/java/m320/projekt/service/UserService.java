package m320.projekt.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import m320.projekt.lib.exceptions.FailedValidationException;
import m320.projekt.lib.interfaces.CrudService;
import m320.projekt.lib.validation.UserValidator;
import m320.projekt.model.User;
import m320.projekt.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService, CrudService<User, Integer> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public User findByIdForUpdate(Integer id) {
        return userRepository.findByIdForUpdate(id).orElseThrow(EntityNotFoundException::new);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public User update(User changing, Integer id) {
        User existing = this.findByIdForUpdate(id);
        merge(existing, changing);
        return userRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void merge(User existing, User changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getUsername() != null) {
            if (StringUtils.isNotBlank(changing.getUsername()) && userValidator.isValidUsername(changing.getUsername()) && !existsByUsername(changing.getUsername()) && !changing.getUsername().equals(existing.getUsername())) {
                existing.setUsername(changing.getUsername());
            } else {
                errors.put("username", List.of("Please choose an other username, this one is either already in use or not valid."));
            }
        }

        if (changing.getEmail() != null) {
            if (StringUtils.isNotBlank(changing.getEmail()) && userValidator.isValidEmail(changing.getEmail()) && !existsByEmail(changing.getEmail()) && !changing.getEmail().equals(existing.getEmail())) {
                existing.setEmail(changing.getEmail());
            } else {
                errors.put("email", List.of("Please choose an other email, this one is not valid."));
            }
        }

        if (changing.getPassword() != null) {
            if (StringUtils.isNotBlank(changing.getPassword()) && userValidator.isValidPassword(changing.getPassword()) && changing.getPassword().equals(existing.getPassword())) {
                String newPassword = passwordEncoder.encode(changing.getPassword());
                existing.setPassword(newPassword);
            } else {
                errors.put("password", List.of("Password must not be empty."));
            }
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()) {
            User user = optUser.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), (Collection<? extends GrantedAuthority>) user.getRole());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
