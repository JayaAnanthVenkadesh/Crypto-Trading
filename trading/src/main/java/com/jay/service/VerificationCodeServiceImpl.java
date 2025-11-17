package com.jay.service;

import com.jay.domain.VerificationType;
import com.jay.modal.User;
import com.jay.modal.VerificationCode;
import com.jay.repsitory.VerificationCodeRepository;
import com.jay.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;


    @Override
    public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
        return null;
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) {
        VerificationCode verificationCode1=new VerificationCode();
        verificationCode1.setOtp(OtpUtils.generateOTP());
        verificationCode1.setVerificationType(verificationType);
        return null;
    }

    @Override
    public VerificationCode getVerificationCodeByCode(Long userId) {
        return null;
    }

    @Override
    public void deleteVerificationCodeById(VerificationCode verificationCode) {

    }
}
