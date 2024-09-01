package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定義定時任務類
 */
@Component
@Slf4j
public class MyTask {

    /**
     * 定時任務，每隔 5 秒觸發一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask(){
        log.info("執行定時任務：{}", new Date());
    }
}
