package com.jay.service;

import com.jay.domain.VerificationType;
import com.jay.modal.ForgotPasswordToken;
import com.jay.modal.User;


public interface ForgotPassword {

    ForgotPasswordToken createToken(User user,
                                    String id, String otp,
                                    VerificationType verificationType,
                                    String sendTo);
}

