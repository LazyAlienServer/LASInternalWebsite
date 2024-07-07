package com.las.lasbackenduser3000.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User表实体类
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**编号**/
    private int id;
    /**注册时间**/
    private long time;
    /**用户名**/
    private String userName;
    /**密码**/
    private String userPassword;
    /**简介**/
    private String userSignature;
    /**标签**/
    private String userTag;
    /**最后登陆时间**/
    private String lastLogin;
    /**白名单**/
    private String whiteList;
    /**正版状态**/
    private int online;
    /**实习期**/
    private String probation;
    /**是否管理**/
    private int administrator;
    /**盐**/
    private int slat;
    /**qq**/
    private String qq;
    /**头像**/
    private String avatar;

}
