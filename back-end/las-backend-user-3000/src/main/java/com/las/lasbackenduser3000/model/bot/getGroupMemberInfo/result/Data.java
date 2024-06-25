package com.las.lasbackenduser3000.model.bot.getGroupMemberInfo.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author sunyinuo
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    private long group_id;
    private long user_id;
    private String nickname;
    private String card;
    private String role;

}
