package com.las.lasbackenduser3000.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackenduser3000.service.api.OnebotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunyinuo
 */
@Service
public class OnebotServiceImpl implements OnebotService {

    public final RestTemplate restTemplate;

    public OnebotServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${onebot11.access_token}")
    private String accessToken;

    /**
     * 群发消息
     *
     * @param groupId 群号
     * @param message 消息
     * @return 返回
     */
    @Override
    public ResponseEntity<JSONObject> sendGroupMsg(long groupId, String message) {
        //参数
        Map body = new HashMap();
        body.put("group_id",groupId);
        body.put("message",message);

        //json body
        String jsonData = JSON.toJSONString(body);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("access_token", accessToken);

        //头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //封装
        HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://server.lazyalienserver.top:3000/send_group_msg").queryParams(map);

        return restTemplate.postForEntity(builder.toUriString(), request, JSONObject.class);
    }

    /**
     * 私发消息
     * @param userId  用户qq
     * @param message 消息
     * @return 返回
     */
    @Override
    public ResponseEntity<JSONObject> sendPrivateMsg(long userId, String message) {
        //参数
        Map body = new HashMap();
        body.put("user_id",userId);
        body.put("message",message);

        //json body
        String jsonData = JSON.toJSONString(body);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("access_token", accessToken);

        //头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //封装
        HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://server.lazyalienserver.top:3000/send_private_msg").queryParams(map);

        return restTemplate.postForEntity(builder.toUriString(), request, JSONObject.class);
    }

    /**
     * 获取群成员列表
     *
     * @param groupId 群号
     * @return 返回json
     */
    @Override
    public ResponseEntity<JSONObject> getGroupMemberList(long groupId) {

        //参数
        Map body = new HashMap();
        body.put("group_id",groupId);


        //json body
        String jsonData = JSON.toJSONString(body);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("access_token", accessToken);

        //头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //封装
        HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://server.lazyalienserver.top:3000/get_group_member_list").queryParams(map);

        return restTemplate.postForEntity(builder.toUriString(), request, JSONObject.class);
    }
}
