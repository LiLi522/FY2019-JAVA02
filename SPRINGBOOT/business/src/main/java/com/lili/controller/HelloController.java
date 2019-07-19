package com.lili.controller;

import com.lili.common.RedisPool;
import com.lili.utils.RedisApi;
import com.lili.utils.ShardedRedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class HelloController {

    @Autowired
    RedisPool redisPool;

    @Value("${server.port}")
    private Integer port;
    @RequestMapping(value = "/test")
    public String test1() {
        return port + "";
    }

    @RequestMapping(value = "/redis")
    public String test() {
        Jedis jedis = redisPool.getJedis();
        jedis.set("re", "xxx");
        return jedis.get("re");
    }
    @Autowired
    RedisApi redisApi;
    @RequestMapping(value = "/testapi")
    public String testApi() {
        redisApi.set("java", "jsp");
        return redisApi.get("java");
    }


    @Autowired
    ShardedRedisApi shardedRedisApi;
    @RequestMapping(value = "/testshard")
    public String testSharded() {
        for (int i = 0; i <= 20; ++i) {
            shardedRedisApi.set("key" + i,"value" +i);
        }
        return shardedRedisApi.get("key1");
    }
}
