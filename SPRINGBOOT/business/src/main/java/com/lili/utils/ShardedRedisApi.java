package com.lili.utils;

import com.lili.common.ShardedRedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

public class ShardedRedisApi {

    @Autowired
    ShardedRedisPool shardedRedisPool;

    /**
     * 字符串
     * */

    public String  set(String key,String value){

        ShardedJedis jedis=shardedRedisPool.getJedis();
        String result=null;
        try{
            result=jedis.set(key, value);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public String  get(String key){


        ShardedJedis jedis=shardedRedisPool.getJedis();
        String result=null;
        try{
            result=jedis.get(key);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public String  setex(String key,int timeout,String value){

        ShardedJedis jedis=shardedRedisPool.getJedis();
        String result=null;
        try{
            result=jedis.setex(key,timeout, value);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }


    /**
     * 设置过期时间
     * */
    public Long  expire(String key,int timeout){

        ShardedJedis jedis=shardedRedisPool.getJedis();

        Long result=null;
        try{
            result=jedis.expire(key,timeout);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }



    /**
     * 在Key不存在的情况下，设置Key-value
     *
     * */
    public Long  setnx(String key,String value){

        ShardedJedis jedis=shardedRedisPool.getJedis();
        Long result=null;
        try{
            result=jedis.setnx(key, value);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 删除Key
     * */
    public Long  del(String key){

        ShardedJedis jedis=shardedRedisPool.getJedis();
        Long result=null;
        try{
            result=jedis.del(key);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }


    public String  getset(String key,String value){

        ShardedJedis jedis=shardedRedisPool.getJedis();
        String result=null;
        try{
            result=jedis.getSet(key,value);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

}
