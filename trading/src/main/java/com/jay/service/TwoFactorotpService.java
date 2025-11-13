package com.jay.service;

import com.jay.modal.TwoFactorOTP;

public interface TwoFactorotpService {

    TwoFactorOTP createTwoFactorOtp(User user,String otp, String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOtp, String otp);

    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOtp);
}
