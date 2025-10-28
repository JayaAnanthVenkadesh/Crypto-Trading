package com.jay.service;

import com.jay.modal.User;
import com.jay.repsitory.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomeUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException

    public UserRepository getUserRepository() {
        User user=userRepository.findByEmail(username);
        return null;
    }
}
