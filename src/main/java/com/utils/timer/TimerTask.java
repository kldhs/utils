package com.utils.timer;

import com.utils.config.ConfigsPoJo;
import com.utils.spring.SpringBootUtil;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xs
 * @date 2021/7/21 16:53
 * 定时器
 */
//标注一个类为Spring容器的Bean
@Component
@PropertySource("classpath:timer-task.properties")
public class TimerTask {
    @Scheduled(cron = "${taskTime}")
    public void tokenPub() {
        try {
            System.err.println("hiahia: " + new Date());
            ConfigsPoJo aa = SpringBootUtil.getBean(ConfigsPoJo.class);
            System.out.println("TimerTask: 定时器任务");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}