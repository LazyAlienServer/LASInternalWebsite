package com.las.lasbackendprojectmanagerialaudits4500.service.db.redis.impl;
import com.las.lasbackendprojectmanagerialaudits4500.service.db.redis.RedisTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyinuo
 */
@Service
@Slf4j
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

    /**
     * 插入数据,无过期时间
     * @param key   k
     * @param value v
     */
    @Override
    public void insert(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Object getByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<String> getByPrex(String prex){
        List<String> getByKey = new ArrayList<>();
        Set<String> set = redisTemplate.keys(prex);
        if (set != null){
            for (String keys : set) {
                getByKey.add((String) getByKey(keys));
            }

            return getByKey;
        }
        return null;
    }


    /**
     * 删除
     *
     * @param key k
     */
    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
