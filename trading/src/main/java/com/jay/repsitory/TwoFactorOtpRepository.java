package com.jay.repsitory;

import com.jay.modal.TwoFactorOTP;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP,String>{
    TwoFactorOTP findByUserId(Long userId);
}


