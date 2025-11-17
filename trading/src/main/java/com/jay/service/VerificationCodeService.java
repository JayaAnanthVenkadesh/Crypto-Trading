package com.jay.service;

import com.jay.domain.VerificationType;
import com.jay.modal.User;
import com.jay.modal.VerificationCode;
import com.jay.repsitory.VerificationCodeRepository;

public interface VerificationCodeService {
    VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id);

    VerificationCode getVerificationCodeByCode(Long userId);

    void deleteVerificationCodeById(VerificationCode verificationCode);
}
