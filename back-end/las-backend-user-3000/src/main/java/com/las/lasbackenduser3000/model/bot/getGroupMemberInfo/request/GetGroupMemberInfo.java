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
public class GetGroupMemberInfo {
    private String action;
    private Params params;
}
