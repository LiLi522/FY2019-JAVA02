package com.lili.common;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

public class ShardedRedisPool {
    private ShardedJedisPool shardedJedisPool;



    public ShardedRedisPool(){}
    public ShardedRedisPool(Integer maxTotal, Integer maxIdle, Integer minIdle,
                            boolean testborrow,
                            boolean testreturn,
                            String ip, Integer port, String password, Integer timeout){

        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);//最大连接数
        jedisPoolConfig.setMaxIdle(maxIdle);//最大空闲数
        jedisPoolConfig.setMinIdle(minIdle);//最小空闲连接数
        jedisPoolConfig.setTestOnBorrow(testborrow);//true:当从连接池获取连接时，检测连接是否有效
        jedisPoolConfig.setTestOnReturn(testreturn);//true:当将连接放回到连接池时，检测连接是否有效
        jedisPoolConfig.setBlockWhenExhausted(true);//true:当连接池的连接耗尽时，会等待知道超时。
//GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password


        try {
            // Scheme://username:password@IP:PORT/dbindex

            URI uri=new URI("http://lyym:"+ URLEncoder.encode("Qq850347097.","UTF-8")+"@39.105.34.71:5379/0");


            //   Scheme://username:password@IP:PORT/dbindex
            URI uri2=new URI("http://taozi:"+ URLEncoder.encode(password,"UTF-8")+"@"+ip+":"+port+"/0");


            System.out.println(uri.getPort());
            System.out.println(uri.getScheme());
            System.out.println(uri.getHost());
            System.out.println(uri.getUserInfo());
            //uri.getUserInfo()
            JedisShardInfo jedisShardInfo=new JedisShardInfo(uri);
            JedisShardInfo jedisShardInfo2=new JedisShardInfo(uri2);

            List<JedisShardInfo> jedisShardInfoList= Lists.newArrayList();
            jedisShardInfoList.add(jedisShardInfo);
            jedisShardInfoList.add(jedisShardInfo2);


            shardedJedisPool=new ShardedJedisPool(jedisPoolConfig,jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


    public ShardedJedis getJedis(){
        return shardedJedisPool.getResource();
    }



    public  void  returnJedis(ShardedJedis jedis){
        shardedJedisPool.returnResource(jedis);
    }

    public  void  returnBrokenResource(ShardedJedis jedis){
        shardedJedisPool.returnBrokenResource(jedis);
    }
}
