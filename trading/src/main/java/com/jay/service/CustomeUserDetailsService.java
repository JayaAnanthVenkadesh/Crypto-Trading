package com.jay.service;

import com.jay.modal.User;
import com.jay.repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException

    public UserRepository getUserRepository() {
        User user=userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorityList=new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorityList);
    }
}
