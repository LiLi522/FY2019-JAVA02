package com.lili;

import com.lili.listener.AppCloseListener;
import com.lili.listener.AppStartListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.lili.dao")
@EnableScheduling
public class BusinessApplication {

    public static void main(String[] args) {

        //SpringApplication.run(BusinessApplication.class, args);
        SpringApplication springApplication = new SpringApplication(BusinessApplication.class);
        springApplication.addListeners(new AppStartListener());
        springApplication.addListeners(new AppCloseListener());
        springApplication.run(args);

    }

}
