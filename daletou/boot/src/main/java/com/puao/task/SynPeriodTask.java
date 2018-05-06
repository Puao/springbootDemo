package com.puao.task;

import com.puao.bean.Ball;
import com.puao.bean.BallIn;
import com.puao.bean.BallRecod;
import com.puao.service.IBallService;
import com.puao.util.JsoupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SynPeriodTask {

    @Autowired
    private IBallService ballService;

    @Scheduled(cron = "0 50 20 ? * MON,THU,SAT")
    public void schedule(){
        Integer max = ballService.getMaxPeriod();

        String url = "http://trend.caipiao.163.com/dlt/?beginPeriod="+max+"&endPeriod=";
        boolean status = false;
        try {
            Map<String, List> map = JsoupUtils.getBall(url);
            List<Ball> balls = map.get("ball");
            List<BallRecod> recodes = map.get("recode");
            List<BallIn> ballIns = map.get("ballIn");
            boolean flag = ballService.insertBallInfo(balls);
            boolean flag2 = ballService.insertBallRecod(recodes);
            boolean flag3 = ballService.insertBallIn(ballIns);
            if (flag == flag2 && flag2 == flag3 && flag ) {
                 status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
