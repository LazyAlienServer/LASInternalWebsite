package com.las.lasbackenduser3000.service.db.api.redis;

/**
 * redis工具类
 * @author sunyinuo
 */
public interface RedisTools{

    /**
     * 插入数据
     * @param key key
     * @param value value
     */
    void insert(String key,Object value);

    /**
     * 根据key查询信息
     * @param key kay
     * @return 返回
     */
    Object getByKey(String key);

}
