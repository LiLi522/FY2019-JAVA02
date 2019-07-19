package com.lili.listener;

import com.lili.utils.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class AppCloseListener implements ApplicationListener<ContextClosedEvent> {
    @Autowired
    RedisApi redisApi;
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        //System.out.println("=======项目的关闭======");
        redisApi.clear();
    }
}
