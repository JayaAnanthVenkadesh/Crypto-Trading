package com.jay.modal;

import com.jay.domain.VerificationType;
import lombok.Data;

@Data
public class TwoFactorAuth {
    // enabling the two factor auth
    private boolean isEnabled = false;
    private VerificationType sendTo;
}

