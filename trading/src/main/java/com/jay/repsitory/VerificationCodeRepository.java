package com.jay.repsitory;

import com.jay.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {


    public VerificationCode findByUserId(Long userId);
}
