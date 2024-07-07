package com.las.lasbackenduser3000.service.db.impl;

import com.las.lasbackenduser3000.mapper.UserMapper;
import com.las.lasbackenduser3000.model.User;
import com.las.lasbackenduser3000.service.db.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * User表service层
 * @author sunyinuo
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 获取所有用户
     * @return 用户实体类List
     */
    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    /**
     * 根据标签
     * @param tag 标签
     * @return 用户实体类List
     */
    @Override
    public List<User> getUserByTag(String tag) {
        return userMapper.getUserByTag(tag);
    }

    /**
     * 根据id查询所有用户
     * @param id id
     * @return 用户实体类
     */
    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    /**
     * 根据userName查询所有用户
     * @param userName 用户名
     * @return 用户实体类
     */
    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    /**
     * 根据userPassword查询所有用户
     * @param userPassword 密码
     * @return 用户实体类
     */
    @Override
    public List<User> getUserByPassword(String userPassword) {
        return userMapper.getUserByPassword(userPassword);
    }

    /**
     * 根据time查询所有用户
     * @param time 时间
     * @return 用户实体类
     */
    @Override
    public List<User> getUserByTime(long time) {
        return userMapper.getUserByTime(time);
    }



    /**
     * 根据最后登陆时间查询用户
     * @param lastLogin 最后登陆时间
     * @return 用户实体类
     */
    @Override
    public List<User> getUserByLastLogin(String lastLogin) {
        return userMapper.getUserByLastLogin(lastLogin);
    }

    /**
     * 根据白名单获取用户
     * @param whiteList 白名单
     * @return 用户实体类
     */
    @Override
    public List<User> getUserByWhiteList(String whiteList) {
        return userMapper.getUserByWhiteList(whiteList);
    }

    /**
     * 根据是否正版获取用户
     * @param online 正版状态 1=正版 0=离线
     * @return 用户实体类
     */
    @Override
    public List<User> getUserByOnline(int online) {
        return userMapper.getUserByOnline(online);
    }

    /**
     * 根据实习期获取用户
     * @param probation 实习期
     * @return 用户实体类
     */
    @Override
    public List<User> getUserByProbation(String probation) {
        return userMapper.getUserByProbation(probation);
    }

    /**
     * 根据qq号获取用户
     *
     * @param qq qq
     * @return 用户实体类
     */
    @Override
    public User getUserByQq(String qq) {
        return userMapper.getUserByQq(qq);
    }

    /**
     * 添加用户
     * @param user 用户实体类
     * @return 是否成功
     */
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    /**
     * 根据userName删除用户
     * @param userName 用户名
     * @return 是否成功
     */
    @Override
    public int deleteUserByName(String userName) {
        return userMapper.deleteUserByName(userName);
    }

    /**
     * 根据是否管理获取用户
     * @param administrator 是否管理 1=是 0=否
     * @return 用户实体类
     */
    @Override
    public List<User> getUserByAdministrator(int administrator) {
        return userMapper.getUserByAdministrator(administrator);
    }

    /**
     * 更新
     * @param user 实体类
     * @return 影响
     */
    @Override
    public int update(User user) {
        return userMapper.update(user);
    }


}
