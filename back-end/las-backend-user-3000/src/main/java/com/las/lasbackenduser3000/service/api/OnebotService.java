package com.las.lasbackenduser3000.service.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;

/**
 * bot接口封装
 * @author sunyinuo
 */
public interface OnebotService {

    /**
     * 群发消息
     * @param groupId 群号
     * @param message 消息
     * @return 返回
     */
    ResponseEntity<JSONObject> sendGroupMsg(long groupId,String message);

    /**
     * 私发消息
     * @param userId 用户qq
     * @param message 消息
     * @return 返回
     */
    ResponseEntity<JSONObject> sendPrivateMsg(long userId,String message);

    /**
     * 获取群成员列表
     * @param groupId 群号
     * @return 返回json
     */
    ResponseEntity<JSONObject> getGroupMemberList(long groupId);
}
