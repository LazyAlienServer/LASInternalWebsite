package com.las.lasbackendprojectmanagerialsubmit4000.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPeople {
    //设计 -1
    public List<String> design;
    //审核 0
    public List<String> audit;
    //测试 1
    public List<String> testing;
    //优化 2
    public List<String> optimization;
    //发布 3
    public List<String> release;
    //实装 4
    public List<String> installation;
    //完成 5
    public List<String> completion;

    public void fill(){
        this.design = new ArrayList<>();
        this.audit = new ArrayList<>();
        this.testing = new ArrayList<>();
        this.optimization = new ArrayList<>();
        this.release = new ArrayList<>();
        this.installation = new ArrayList<>();
        this.completion = new ArrayList<>();
    }

}
