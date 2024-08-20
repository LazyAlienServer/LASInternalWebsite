package com.las.lasbackendprojectmanagerialaudits4500.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.las.lasbackendprojectmanagerialaudits4500.model.Place;
import com.las.lasbackendprojectmanagerialaudits4500.model.Submit;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.ProjectCRUDService;
import com.las.lasbackendprojectmanagerialaudits4500.service.db.redis.impl.RedisToolsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyinuo
 */
@Service
@Slf4j
public class ProjectCRUDServiceImpl implements ProjectCRUDService {

    private final RedisToolsImpl redisTools;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public ProjectCRUDServiceImpl(RedisToolsImpl redisTools) {
        this.redisTools = redisTools;
    }


    @Override
    public List<Submit> GET() {

        List<Submit> projects=new ArrayList<>();
        try{
            for(String project:redisTools.getByPrex("PROJECT_*")){
                projects.add(objectMapper.readValue(project, Submit.class));
            }
        }catch (IOException e){
            log.error("GetProjectListService: getProjectList",e);
        }

        return projects;
    }

    /**
     * 根据名称获取项目
     * @param name name
     * @return json
     */
    @Override
    public String getProjectByName(String name) {
        List<Submit> getProjectByName=new ArrayList<>();

        for (Submit project : GET()) {
            if (project.getName().equals(name)){
                getProjectByName.add(project);
            }
        }
        return JSON.toJSONString(getProjectByName.toArray());
    }

    /**
     * 根据项目负责人筛选项目
     * @param authors 负责人
     * @return json
     */
    @Override
    public String getProjectByAuthors(List<String> authors) {

        List<Submit> getProjectByAuthors= new ArrayList<>();

        for (Submit project : GET()) {
            for (String author : project.getAuthors()) {
                for (String a : authors) {
                    if (author.equals(a)){
                       getProjectByAuthors.add(project);
                    }
                }
            }
        }
        return JSON.toJSONString(getProjectByAuthors.toArray());
    }

    /**
     * 根据项目(预计)开始时间获取项目
     * @param startTime 开始时间
     * @return json
     */
    @Override
    public String getProjectByStartTime(String startTime) {

        List<Submit> getProjectByStartTime=new ArrayList<>();

        for (Submit project : GET()) {
            if (project.getStart_time().equals(startTime)){
                getProjectByStartTime.add(project);
            }
        }
        return JSON.toJSONString(getProjectByStartTime.toArray());
    }

    /***
     * 根据位置查询项目
     * @param place 位置
     * @return json
     */
    @Override
    public String getProjectByPlace(Place place,boolean onlyDimension) {
        List<Submit> getProjectByPlace=new ArrayList<>();

        for (Submit project : GET()) {
            //判断是否只查询维度
            if (onlyDimension){
                if (project.getPlace().getDimension().equals(place.getDimension())){
                    getProjectByPlace.add(project);
                }
            }else{
                if (project.getPlace() == place){
                    getProjectByPlace.add(project);
                }
            }
        }

        return JSON.toJSONString(getProjectByPlace.toArray());
    }

    /**
     * 根据是否原创获取项目
     * @param innovation 是否原创
     * @return json
     */
    @Override
    public String getProjectByInnovation(boolean innovation) {
        List<Submit> getProjectByInnovation=new ArrayList<>();

        for (Submit project : GET()) {
            if (project.getInnovation() == innovation){
                getProjectByInnovation.add(project);
            }
        }
        return JSON.toJSONString(getProjectByInnovation.toArray());
    }

    /**
     * 根据类型获取项目
     * @param type 类型
     * @return json
     */
    @Override
    public String getProjectByType(List<String> type) {
        List<Submit> getProjectByType=new ArrayList<>();

        for (Submit project : GET()) {
            for (String typePro : project.getType()) {
                for (String t : type) {
                    if (typePro.equals(t)){
                        getProjectByType.add(project);
                    }
                }
            }
        }
        return JSON.toJSONString(getProjectByType.toArray());
    }

    /**
     * 根据子项目编号获取项目
     * @param children 子项目编号
     * @return json
     */
    @Override
    public String getProjectByChildren(List<String> children) {
        List<Submit> getProjectByChildren=new ArrayList<>();

        for (Submit project : GET()) {
            for (String child : project.getChildren()) {
                for (String c : children) {
                    if (child.equals(c)){
                        getProjectByChildren.add(project);
                    }
                }
            }
        }

        return JSON.toJSONString(getProjectByChildren.toArray());
    }

    /**
     * 根据是否通过获取项目
     * @param pass 是否通过
     * @return json
     */
    @Override
    public String getProjectByPass(boolean pass) {
        List<Submit> getProjectByPass=new ArrayList<>();

        for (Submit project : GET()) {
            if (project.getPass() == pass){
                getProjectByPass.add(project);
            }
        }
        return JSON.toJSONString(getProjectByPass.toArray());
    }

    /**
     * 根据审核人获取项目
     * @param auditor 审核人
     * @return json
     */
    @Override
    public String getProjectByAuditor(String auditor) {
        List<Submit>getProjectByAuditor=new ArrayList<>();

        for (Submit project : GET()) {
            if (project.getAuditor().equals(auditor)){
                getProjectByAuditor.add(project);
            }
        }
        return JSON.toJSONString(getProjectByAuditor.toArray());
    }

    /**
     * 根据进度获取项目
     * @param progress 项目进度
     * @return json
     */
    @Override
    public String getProjectByProgress(int progress) {
        List<Submit>getProjectByProgress=new ArrayList<>();

        for (Submit project : GET()) {
            if (project.getProgress() == progress){
                getProjectByProgress.add(project);
            }
        }
        return JSON.toJSONString(getProjectByProgress.toArray());
    }
}
