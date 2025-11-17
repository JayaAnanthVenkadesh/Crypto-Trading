package com.jay.controller;

import com.jay.config.JwtProvider;
import com.jay.modal.TwoFactorOTP;
import com.jay.modal.User;
import com.jay.repsitory.UserRepository;
import com.jay.response.AuthResponse;
import com.jay.service.CustomeUserDetailsService;
import com.jay.service.TwoFactorotpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

    @Autowired
    private TwoFactorotpService twoFactorotpService;

    @Autowired
    private EmailService emailService;

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

        User authuser=userRepository.findByEmail(userName);

        if(user.getTwoFactorAuth().isEnabled()){
            AuthResponse res=new AuthResponse();
            res.setMessage("Two factor auth is enabled");
            res.setStatus(true);
            res.setTwoFactorAuthEnabled(true);
            String otp=otpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP=TwoFactorOTTPService.findByUser(user.getId());
            if(oldTwoFactorOTP!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }

            TwoFactorOTP newTwoFactorOTP=twoFactorOtpService.createTwoFactorOtp(authUser, otp, jwt);

            emailService.sendVerificationOtpEmail(userName, otp);

            res.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }


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
@PostMapping("/two-factor/otp/{otp}")
public ResponseEntity<AuthResponse> verifySigningOtp(
        @PathVariable String otp,
        @RequestParam String id) throws Exception {

    TwoFactorOTP twoFactorOTP=twoFactorOtpService.findById(id);

    if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)){
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Two factor authentication verified");
        authResponse.setTwoFactorAuthEnabled(true);
        authResponse.setJwt(twoFactorOTP.getJwt());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
    throw new Exception("invalid otp");
}
    }
