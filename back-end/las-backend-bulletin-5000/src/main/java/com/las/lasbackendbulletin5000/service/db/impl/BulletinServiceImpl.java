package com.las.lasbackendbulletin5000.service.db.impl;

import com.las.lasbackendbulletin5000.mapper.BulletinMapper;
import com.las.lasbackendbulletin5000.model.Bulletin;
import com.las.lasbackendbulletin5000.service.db.BulletinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sunyinuo
 */
@Service
public class BulletinServiceImpl implements BulletinService {

    @Resource
    private BulletinMapper bulletinMapper;


    /**
     * 获取公告列表
     * @return list
     */
    @Override
    public List<Bulletin> getBulletinList() {
        return bulletinMapper.getBulletinList();
    }

    /**
     * 根据ID查询公告
     * @param id id
     * @return result
     */
    @Override
    public Bulletin getBulletinById(int id) {
        return bulletinMapper.getBulletinById(id);
    }

    /**
     * 根据提交人获取帖子
     * @param publisher 提交人
     * @return list
     */
    @Override
    public List<Bulletin> getBulletinByPublisher(String publisher) {
        return bulletinMapper.getBulletinByPublisher(publisher);
    }

    /**
     * 根据等级查询公告
     *
     * @param level 等级
     * @return list
     */
    @Override
    public List<Bulletin> getBulletinByLevel(int level) {
        return bulletinMapper.getBulletinByLevel(level);
    }

    /**
     * 根据发布时间查询公告
     * @param time 发布时间
     * @return list
     */
    @Override
    public List<Bulletin> getBulletinByTime(String time) {
        return bulletinMapper.getBulletinByTime(time);
    }

    /**
     * 根据标题获取公告
     * @param title title
     * @return result
     */
    @Override
    public Bulletin getBulletinByTitle(String title) {
        return bulletinMapper.getBulletinByTitle(title);
    }

    /**
     * 根据是否置顶获取公告
     * @param sticky 是否置顶
     * @return list
     */
    @Override
    public List<Bulletin> getBulletinBySticky(boolean sticky) {
        return bulletinMapper.getBulletinBySticky(sticky);
    }

    /**
     * 添加公告
     * @param bulletin 实体来
     * @return 影响
     */
    @Override
    public int addBulletin(Bulletin bulletin) {
        return bulletinMapper.addBulletin(bulletin);
    }

    /**
     * 根据标题删除公告
     * @param title 标题
     * @return 影响
     */
    @Override
    public int deleteBulletinByTitle(String title) {
        return bulletinMapper.deleteBulletinByTitle(title);
    }
}
