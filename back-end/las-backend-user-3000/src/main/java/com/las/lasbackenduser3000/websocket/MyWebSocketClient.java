package com.las.lasbackenduser3000.websocket;

import com.alibaba.fastjson.JSON;
import com.las.lasbackenduser3000.model.bot.getGroupMemberInfo.result.Data;
import com.las.lasbackenduser3000.model.bot.getGroupMemberInfo.result.GetGroupMemberInfoResult;
import com.las.lasbackenduser3000.service.db.redis.impl.RedisToolsImpl;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyinuo
 */
@Slf4j
public class MyWebSocketClient extends WebSocketClient {

    /**获取群成员信息时成功时的的retcode**/
    public final int GET_GROUP_MEMBER_INFO_SUCCESSES = 0;


    @Resource
    public RedisToolsImpl redisTools;


    public MyWebSocketClient(URI serverUri, Map<String,String> headers) {
        super(serverUri,headers);
    }


    @Override
    public void onOpen(ServerHandshake arg0) {
        log.info("------ MyWebSocketClient onOpen ------");
        /*
        send("{\n" +
                "    \"action\": \"get_group_member_info\",\n" +
                "    \"params\": {\n" +
                "        \"group_id\": 826055741,\n" +
                "        \"user_id\": 2192519304\n" +
                "    }    \n" +
                "}");

         */
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        log.info("------ WebSocket onClose ------{}",arg1);
    }


    @Override
    public void onError(Exception arg0) {
        log.error("------ WebSocket onError ------{}",arg0);
    }

    @Override
    public void onMessage(String response) {
        //ws消息=>GetGroupMemberInfoResult.class
        GetGroupMemberInfoResult getGroupMemberInfoResult = JSON.parseObject(response, GetGroupMemberInfoResult.class);
        //查询到
        if (getGroupMemberInfoResult.getRetcode() == GET_GROUP_MEMBER_INFO_SUCCESSES) {
            //get user_id,role
            Data data = getGroupMemberInfoResult.getData();
            String userId ="INFO_" + Long.toString(data.getUser_id());
            String role = data.getRole();
            //redis insert
            redisTools.insert(userId,role);
        }else {
            log.error("------ WebSocket onMessage ------{}",response);
        }
    }


}

