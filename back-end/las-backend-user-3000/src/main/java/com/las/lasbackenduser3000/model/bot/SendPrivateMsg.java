package com.las.lasbackenduser3000.model.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendPrivateMsg {
    private String action;
    private Params params;
}
