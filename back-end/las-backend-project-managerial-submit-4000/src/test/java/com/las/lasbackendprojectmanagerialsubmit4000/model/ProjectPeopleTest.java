package com.las.lasbackendprojectmanagerialsubmit4000.model;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class ProjectPeopleTest {


    @Test
    void fill() {
        ProjectPeople projectPeople = new ProjectPeople();
        projectPeople.fill();

        System.out.println(JSONObject.toJSONString(projectPeople));
    }
}