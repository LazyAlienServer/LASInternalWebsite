package com.las.lasbackenduser3000.service.api.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class OnebotServiceImplTest {

    @Autowired
    public OnebotServiceImpl onebotService;

    @Test
    void sendGroupMsg() {
        System.out.println(onebotService.sendGroupMsg(826055741, "sendGroupMsg Interface/Junit5"));
    }

    @Test
    void sendPrivateMsg() {
        System.out.println(onebotService.sendPrivateMsg(2192519304L, "sendPrivateMsg Interface/Junit5"));
    }

    @Test
    void  getGroupMemberList(){
        System.out.println(onebotService.getGroupMemberList(826055741));
    }
}
