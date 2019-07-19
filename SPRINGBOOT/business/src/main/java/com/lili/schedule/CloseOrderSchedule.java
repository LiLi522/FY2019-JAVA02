package com.lili.schedule;

import com.lili.Service.IOrderService;
import com.lili.utils.Const;
import com.lili.utils.ShardedRedisApi;
import org.apache.commons.lang.time.DateUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CloseOrderSchedule {

    @Value("${order.close.timeout}")
    private int closeTimeout;

    @Autowired
    IOrderService orderService;

    @Autowired
    ShardedRedisApi shardedRedisApi;

    @Value("${lock.timeout}")
    private Integer lockTimeout;

    @Scheduled(cron = "0 0 */1 * * *")
    public void closeOrder() {
        //step1:获取redis的锁
        Long result=shardedRedisApi.setnx(Const.LOCK_KEY,String.valueOf(System.currentTimeMillis()+lockTimeout));
        if(result==1){ //获取redis锁
            //设置锁的过期时间
            close();
        }else{
            //是否有权利获取锁
            String value=shardedRedisApi.get(Const.LOCK_KEY);
            if(value==null||(value!=null&& System.currentTimeMillis()>Long.parseLong(value))){
                //当前线程有权利获取锁      getset
                String oldValue=shardedRedisApi.getset(Const.LOCK_KEY,String.valueOf(System.currentTimeMillis()+lockTimeout));
                if(value.equals(oldValue)){//同一个请求
                    close();
                }else{
                    System.out.println("没有获取到锁");
                }
            }
        }


        //System.out.println("==========cron" + System.currentTimeMillis());
    }

    public void close() {
        shardedRedisApi.expire(Const.LOCK_KEY,20);
        Date closeOrderTime = DateUtils.addHours(new Date(), -closeTimeout);

        orderService.closeOrder(com.lili.utils.DateUtils.dateToStr(closeOrderTime));
        shardedRedisApi.del(Const.LOCK_KEY);
    }


}
