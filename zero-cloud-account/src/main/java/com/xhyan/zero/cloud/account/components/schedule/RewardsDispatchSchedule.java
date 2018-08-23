package com.xhyan.zero.cloud.account.components.schedule;

import com.xhyan.zero.cloud.account.service.RewardsDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 奖励发放定时任务
 *
 * @author xhyan
 */
@Component
public class RewardsDispatchSchedule {

    @Autowired
    private RewardsDispatchService rewardsDispatchService;

    /**
     * 每小时执行一次奖励发放
     *
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void dispatchRewards() {
        rewardsDispatchService.dispatch();
    }
}
