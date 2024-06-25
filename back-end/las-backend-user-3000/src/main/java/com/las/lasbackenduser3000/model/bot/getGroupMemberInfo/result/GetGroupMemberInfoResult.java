package com.las.lasbackenduser3000.model.bot.getGroupMemberInfo.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author sunyinuo
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupMemberInfoResult {

    private String status;
    private int retcode;
    private Data data;
    private String message;
    private String wording;

}
