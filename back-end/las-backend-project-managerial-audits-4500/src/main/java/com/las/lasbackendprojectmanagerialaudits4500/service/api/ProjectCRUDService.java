package com.las.lasbackendprojectmanagerialaudits4500.service.api;

import com.las.lasbackendprojectmanagerialaudits4500.model.Place;
import com.las.lasbackendprojectmanagerialaudits4500.model.Submit;

import java.util.List;

/**
 * @author sunyinuo
 */
public interface ProjectCRUDService {


    List<Submit> GET();


    /**
     * 根据名称获取项目
     * @param name name
     * @return json
     */
    String getProjectByName(String name);

    /**
     * 根据项目负责人筛选项目
     * @param authors 负责人
     * @return json
     */
    String getProjectByAuthors(List<String> authors);

    /**
     * 根据项目(预计)开始时间获取项目
     * @param startTime 开始时间
     * @return json
     */
    String getProjectByStartTime(String startTime);

    /***
     * 根据位置查询项目
     * @param place 位置
     * @param onlyDimension onlyDimension?
     * @return json
     */
    String getProjectByPlace(Place place,boolean onlyDimension);

    /**
     * 根据是否原创获取项目
     * @param innovation 是否原创
     * @return json
     */
    String getProjectByInnovation(boolean innovation);


    /**
     * 根据类型获取项目
     * @param type 类型
     * @return json
     */
    String getProjectByType(List<String> type);

    /**
     * 根据子项目编号获取项目
     * @param children 子项目编号
     * @return json
     */
    String getProjectByChildren(List<String> children);

    /**
     * 根据是否通过获取项目
     * @param pass 是否通过
     * @return json
     */
    String getProjectByPass(boolean pass);

    /**
     * 根据审核人获取项目
     * @param auditor 审核人
     * @return json
     */
    String getProjectByAuditor(String auditor);

    /**
     * 根据进度获取项目
     * @param progress 项目进度
     * @return json
     */
    String getProjectByProgress(int progress);
}
