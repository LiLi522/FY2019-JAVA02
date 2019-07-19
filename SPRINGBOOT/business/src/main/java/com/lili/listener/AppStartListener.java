package com.lili.listener;


import com.lili.utils.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

//监听项目的启动
public class AppStartListener implements ApplicationListener<ContextRefreshedEvent> {



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //System.out.println("========项目的启动=======");



    }
}
