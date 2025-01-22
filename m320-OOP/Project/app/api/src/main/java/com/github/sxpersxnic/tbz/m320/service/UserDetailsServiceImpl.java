package com.github.sxpersxnic.tbz.m320.service;


import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Is used by AuthenticationManager for authenticate method
 *
 * @author sxpersxnic
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optPerson = userRepository.findUserByEmail(email);

        if (optPerson.isPresent()) {
            User user = optPerson.get();
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAssignedRoles());
        } else {
            throw new UsernameNotFoundException(email);
        }
    }

}
