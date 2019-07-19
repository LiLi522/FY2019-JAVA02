package com.lili.utils;

import com.lili.common.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;


public class RedisApi {


    @Autowired
    RedisPool redisPool;

    /**
     * 字符串
     * */

    public String  set(String key,String value){

        Jedis jedis=redisPool.getJedis();
        String result=null;
        try{
            result=jedis.set(key, value);
            redisPool.returnJedis(jedis);
        }catch (Exception e){
            redisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public String  get(String key){


        Jedis jedis=redisPool.getJedis();
        String result=null;
        try{
            result=jedis.get(key);
            redisPool.returnJedis(jedis);
        }catch (Exception e){
            redisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @param value
     * @return
     */
    public String  setex(String key,int timeout,String value){

        Jedis jedis=redisPool.getJedis();
        String result=null;
        try{
            result=jedis.setex(key,timeout, value);
            redisPool.returnJedis(jedis);
        }catch (Exception e){
            redisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 设置key的过期时间
     * @param key
     * @param timeout
     * @return
     */
    public Long  expire(String key,int timeout){

        Jedis jedis=redisPool.getJedis();

        Long result=null;
        try{
            result=jedis.expire(key,timeout);
            redisPool.returnJedis(jedis);
        }catch (Exception e){
            redisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public void  clear(){

        Jedis jedis=redisPool.getJedis();

        Long result=null;
        try{
            jedis.flushDB();
            redisPool.returnJedis(jedis);
        }catch (Exception e){
            redisPool.returnBrokenResource(jedis);
        }

    }


}