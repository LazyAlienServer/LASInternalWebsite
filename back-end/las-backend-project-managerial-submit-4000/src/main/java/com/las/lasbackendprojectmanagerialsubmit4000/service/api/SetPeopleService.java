package com.las.lasbackendprojectmanagerialsubmit4000.service.api;

import result.Result;

import java.util.List;

/**
 * @author sunyinuo
 */
public interface SetPeopleService {
    /**
     * 设置人员
     * @param projectName 项目名
     * @param people 人
     * @param progress 进度
     * @return 统一返回
     */
    Result setPeople(String projectName, List<String> people,int progress);
}
