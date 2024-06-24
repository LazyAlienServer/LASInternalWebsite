package com.las.lasbackenduser3000.mapper;


import com.las.lasbackenduser3000.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * User表mapper
 * @author sunyinuo
 */
@Mapper
public interface UserMapper {
    /**
     * 获取所有用户
     * @return 用户实体类List
     */
    List<User> getUserList();

    /**
     * 根据标签
     * @param tag 标签
     * @return 用户实体类List
     */
    List<User> getUserByTag(String tag);


    /**
     * 根据id查询所有用户
     * @param id id
     * @return 用户实体类
     */
    User getUserById(int id);

    /**
     * 根据userName查询所有用户
     * @param userName 用户名
     * @return 用户实体类
     */
    User getUserByName(String userName);

    /**
     * 根据userPassword查询所有用户
     * @param userPassword 密码
     * @return 用户实体类
     */
    List<User> getUserByPassword(String userPassword);

    /**
     * 根据time查询所有用户
     * @param time 时间
     * @return 用户实体类
     */
    List<User> getUserByTime(long time);


    /**
     * 根据最后登陆时间查询用户
     * @param lastLogin 最后登陆时间
     * @return 用户实体类
     */
    List<User> getUserByLastLogin(String lastLogin);

    /**
     * 根据白名单获取用户
     * @param whiteList 白名单
     * @return 用户实体类
     */
    List<User> getUserByWhiteList(String whiteList);

    /**
     * 根据是否正版获取用户
     * @param online 正版状态 1=正版 0=离线
     * @return 用户实体类
     */
    List<User> getUserByOnline(int online);

    /**
     * 根据实习期获取用户
     * @param probation 实习期
     * @return 用户实体类
     */
    List<User> getUserByProbation(String probation);

    /**
     * 添加用户
     * @param user 用户实体类
     * @return 影响
     */
    int addUser(User user);

    /**
     * 根据userName删除用户
     * @param userName 用户名
     * @return 影响
     */
    int deleteUserByName(String userName);

    /**
     * 根据是否管理获取用户
     * @param administrator 是否管理 1=是 0=否
     * @return 用户实体类
     */
    List<User> getUserByAdministrator(int administrator);

}
