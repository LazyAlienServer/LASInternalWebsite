package com.las.lasbackenduser3000.model.bot.sendPrivateMsg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params {


    private long user_id;
    private String message;
}
