package com.las.lasbackendbulletin5000.mapper;

import com.las.lasbackendbulletin5000.model.Bulletin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sunyinuo
 */
@Mapper
public interface BulletinMapper {
    /**
     * 获取公告列表
     * @return list
     */
   List<Bulletin> getBulletinList();

    /**
     * 根据ID查询公告
     * @param id id
     * @return result
     */
   Bulletin getBulletinById(int id);

    /**
     * 根据提交人获取帖子
     * @param publisher 提交人
     * @return list
     */
   List<Bulletin> getBulletinByPublisher(String publisher);

    /**
     * 根据等级查询公告
     * @param level 等级
     * @return list
     */
    List<Bulletin> getBulletinByLevel(int level);


    /**
     * 根据发布时间查询公告
     * @param time 发布时间
     * @return list
     */
    List<Bulletin> getBulletinByTime(String time);

    /**
     * 根据标题获取公告
     * @param title title
     * @return result
     */
    Bulletin  getBulletinByTitle(String title);

    /**
     * 根据是否置顶获取公告
     * @param sticky 是否置顶
     * @return list
     */
    List<Bulletin> getBulletinBySticky(boolean sticky);

    /**
     * 添加公告
     * @param bulletin 实体来
     * @return 影响
     */
    int addBulletin(Bulletin bulletin);

    /**
     * 根据标题删除公告
     * @param title 标题
     * @return 影响
     */
    int deleteBulletinByTitle (String title);
}
