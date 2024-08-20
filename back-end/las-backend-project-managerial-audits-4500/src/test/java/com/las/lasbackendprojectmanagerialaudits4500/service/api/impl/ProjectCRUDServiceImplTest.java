package com.las.lasbackendprojectmanagerialaudits4500.service.api.impl;

import com.las.lasbackendprojectmanagerialaudits4500.model.Place;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ProjectCRUDServiceImplTest {

    @Autowired
    public ProjectCRUDServiceImpl projectCRUDService;

    @Test
    void GET() {
        System.out.println(projectCRUDService.GET());
    }

    @Test
    void getProjectByName() {
        System.out.println(projectCRUDService.getProjectByName("name7"));
    }

    @Test
    void getProjectByAuthors() {
        System.out.println(projectCRUDService.getProjectByAuditor("fanzhi"));
    }

    @Test
    void getProjectByStartTime() {
        System.out.println(projectCRUDService.getProjectByStartTime("123456"));
    }

    @Test
    void getProjectByPlace() {
        System.out.println(projectCRUDService.getProjectByPlace(new Place("b",0,0,0),true));
    }

    @Test
    void getProjectByInnovation() {
        System.out.println(projectCRUDService.getProjectByInnovation(true));
    }

    @Test
    void getProjectByType() {
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("-1");
        System.out.println(projectCRUDService.getProjectByType(list));
    }

    @Test
    void getProjectByChildren() {
        List<String> list = new ArrayList<>();
        list.add("name77");
        System.out.println(projectCRUDService.getProjectByChildren(list));
    }

    @Test
    void getProjectByPass() {
        System.out.println(projectCRUDService.getProjectByPass(true));
    }

    @Test
    void getProjectByAuditor() {
        List<String> list = new ArrayList<>();
        list.add("martin");
        list.add("fanzhi");
        list.add("tanh");
        System.out.println(projectCRUDService.getProjectByAuthors(list));
    }

    @Test
    void getProjectByProgress() {
        System.out.println(projectCRUDService.getProjectByProgress(-1));
    }
}