package com.lili.common;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private JedisPool jedisPool;
    public RedisPool() {}
    public RedisPool(Integer maxTotal, Integer maxIdle,
                     Integer minIdle, boolean testborrow,
                     boolean testreturn, String ip,
                     Integer port, String password,
                     Integer timeout) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);//最大连接数
        jedisPoolConfig.setMaxIdle(maxIdle);//最大空闲数
        jedisPoolConfig.setMinIdle(minIdle);//最小连接数
        jedisPoolConfig.setTestOnBorrow(testborrow);//true:当从连接池获取连接时，检测连接是否有效
        jedisPoolConfig.setTestOnReturn(testreturn);//true:当将连接放回到连接池时，检测连接是否有效
        jedisPoolConfig.setBlockWhenExhausted(true);//true:当连接池的连接耗尽时，会等待直到超时

        jedisPool = new JedisPool(jedisPoolConfig, ip, port, timeout, password);

    }
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void returnJedis(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }

    public void returnBrokenResource(Jedis jedis) {
        jedisPool.returnBrokenResource(jedis);
    }
}
