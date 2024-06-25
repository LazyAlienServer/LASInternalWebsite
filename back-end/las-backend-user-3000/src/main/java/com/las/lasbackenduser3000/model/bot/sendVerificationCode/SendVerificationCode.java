package com.las.lasbackenduser3000.model.bot.sendVerificationCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendVerificationCode {
    private String message;
    private String qq;
}
