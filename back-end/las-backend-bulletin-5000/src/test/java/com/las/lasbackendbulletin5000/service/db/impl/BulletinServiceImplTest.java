package com.las.lasbackendbulletin5000.service.db.impl;

import com.las.lasbackendbulletin5000.model.Bulletin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BulletinServiceImplTest {

    @Autowired
    public BulletinServiceImpl bulletinService;
    @Test
    void addBulletin() {
        System.out.println(bulletinService.addBulletin(new Bulletin(0, "fanzhi", 0, "122222", "test title", "body", true)));
        System.out.println(bulletinService.addBulletin(new Bulletin(0, "martin", 0, "122222", "测试", "body", false)));
    }

    @Test
    void getBulletinList() {
        System.out.println(bulletinService.getBulletinList());
    }

    @Test
    void getBulletinById() {
        System.out.println(bulletinService.getBulletinById(1));
    }

    @Test
    void getBulletinByPublisher() {
        System.out.println(bulletinService.getBulletinByPublisher("fanzhi"));
    }

    @Test
    void getBulletinByLevel() {
        System.out.println(bulletinService.getBulletinByLevel(0));
    }

    @Test
    void getBulletinByTime() {
        System.out.println(bulletinService.getBulletinByTime("122222"));
    }

    @Test
    void getBulletinByTitle() {
        System.out.println(bulletinService.getBulletinByTitle("测试"));
    }

    @Test
    void getBulletinBySticky() {
        System.out.println(bulletinService.getBulletinBySticky(true));
    }


    @Test
    void deleteBulletinByTitle() {
        System.out.println(bulletinService.deleteBulletinByTitle("测试"));
        System.out.println(bulletinService.deleteBulletinByTitle("test title"));
    }
}