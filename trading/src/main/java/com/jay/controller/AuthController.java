package com.jay.controller;

import com.jay.config.JwtProvider;
import com.jay.modal.User;
import com.jay.repsitory.UserRepository;
import com.jay.service.CustomeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user){


        User isEmailExist=userRepository.findByEmail(user.getEmail());

        if(isEmailExist!=null){
            throw new Exception("email is already used with another account");
        }


        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(newUser);

        Authentication auth=new UsernamePasswordAuthenticationToken(
                user.getEmail()
                user.getPassword()

        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);

        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login success");


        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }
}
    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

@PostMapping("/signin")
public ResponseEntity<AuthResponse> login(@RequestBody User user){


    User isEmailExist=userRepository.findByEmail(user.getEmail());

    if(isEmailExist!=null){
        throw new Exception("email is already used with another account");
    }

    String userName=user.getEmail();
    String password=user.getPassword();



    Authentication auth=authenticate(userName,password);

    SecurityContextHolder.getContext().setAuthentication(auth);

    String jwt= JwtProvider.generateToken(auth);

    AuthResponse res=new AuthResponse();
    res.setJwt(jwt);
    res.setStatus(true);
    res.setMessage("register success");


    return new ResponseEntity<>(res, HttpStatus.CREATED);

}

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);

        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
    }
        if(!password.equals(userDetails.getPassword())){
        throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
