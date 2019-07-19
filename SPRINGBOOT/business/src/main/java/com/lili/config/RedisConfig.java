package com.lili.config;

import com.lili.common.RedisPool;
import com.lili.common.ShardedRedisPool;
import com.lili.utils.RedisApi;
import com.lili.utils.ShardedRedisApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${redis.max.total}")
    private  Integer maxTotal;

    @Value("${redis.max.idle}")
    private  Integer maxIdle;
    @Value("${redis.min.idle}")
    private  Integer minIdle;
    @Value("${redis.test.borrow}")
    private  boolean testborrow;
    @Value("${redis.test.return}")
    private  boolean testreturn;
    @Value("${redis.ip}")
    private  String ip;
    @Value("${redis.port}")
    private  Integer port;
    @Value("${redis.password}")
    private  String password;
    @Value("${redis.timeout}")
    private  Integer timeout;

    //Integer maxTotal,Integer maxIdle,Integer minIdle,
    //                     boolean testborrow,
    //                     boolean testreturn,
    //                     String ip,Integer port,String password,Integer timeout
    @Bean
    public RedisPool redisPool(){

        return new RedisPool(maxTotal,
                maxIdle,
                minIdle,
                testborrow,
                testreturn,ip,
                port,
                password,
                timeout);
    }

    @Bean
    public ShardedRedisPool shardedRedisPool(){

        return new ShardedRedisPool(maxTotal,
                maxIdle,
                minIdle,
                testborrow,
                testreturn,ip,
                port,
                password,
                timeout);
    }

    @Bean
    public RedisApi redisApi() {
        return new RedisApi();
    }
    @Bean
    public ShardedRedisApi shardedRedisApi() {
        return new ShardedRedisApi();
    }
    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public boolean isTestborrow() {
        return testborrow;
    }

    public void setTestborrow(boolean testborrow) {
        this.testborrow = testborrow;
    }

    public boolean isTestreturn() {
        return testreturn;
    }

    public void setTestreturn(boolean testreturn) {
        this.testreturn = testreturn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
