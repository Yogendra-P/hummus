package com.unbxd.hummus.dao;

import com.google.inject.Provider;
import com.unbxd.hummus.dao.impl.RedisDao;
import redis.clients.jedis.Jedis;


public class DaoProvider implements Provider<SchemaDao> {
    public SchemaDao get()
    {
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection Successful");
        System.out.println("The server is running " + jedis.ping());
        RedisDao redisDao = new RedisDao(jedis);
        return redisDao;
    }
}