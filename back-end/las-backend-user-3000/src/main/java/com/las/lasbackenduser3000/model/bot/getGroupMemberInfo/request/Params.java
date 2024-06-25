package com.las.lasbackenduser3000.model.bot.getGroupMemberInfo.request;

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

    private long group_id;
    private long user_id;
}
