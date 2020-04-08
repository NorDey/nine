package com.BYS.GWSystem;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.BYS.GWSystem.service.IPostService;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
	
	@Autowired
	private IPostService iPostService;
	
    //3.添加定时任务
    //或直接指定时间间隔，例如：10秒
    //@Scheduled(fixedRate=10000)
    @Scheduled(cron = "0 */1 * * * ?")
    private void configureTasks() {
       // System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    	
    	 //设置热门岗位
    	iPostService.setUpPopularPositions();
    }
}
