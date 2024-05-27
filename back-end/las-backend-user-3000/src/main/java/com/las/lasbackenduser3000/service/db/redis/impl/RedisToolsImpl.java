package com.las.lasbackenduser3000.service.db.redis.impl;

import com.las.lasbackenduser3000.service.db.redis.RedisTools;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyinuo
 */
@Service
public class RedisToolsImpl implements RedisTools {

    @Resource
    public RedisTemplate<String,Object> redisTemplate;

    /**
     * 插入数据
     * @param key   key
     * @param value value
     */
    @Override
    public void insert(String key, Object value,long timeout, TimeUnit unit) {
       redisTemplate.opsForValue().set(key,value,timeout, unit);
    }

    @Override
    public Object getByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
