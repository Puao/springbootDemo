package com.puao.controller;

import com.puao.bean.Ball;
import com.puao.bean.BallIn;
import com.puao.bean.BallRecod;
import com.puao.service.IBallService;
import com.puao.util.JsoupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/ball")
public class InitDataController {

    @Autowired
    private IBallService ballService;


    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        Integer period = ballService.getMaxPeriod();
        model.addObject("endPeriod",period);
        return model;
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public List<Ball> getBall() {
        return ballService.getBallInfo();
    }

    @ResponseBody
    @RequestMapping("/init")
    public String initData() {

        String url = "http://trend.caipiao.163.com/dlt/?beginPeriod=17001&endPeriod=18050";
        try {
            Map<String, List> map = JsoupUtils.getBall(url);
            List<Ball> balls = map.get("ball");
            List<BallRecod> recodes = map.get("recode");
            List<BallIn> ballIns = map.get("ballIn");
            boolean flag = ballService.insertBallInfo(balls);
            boolean flag2 = ballService.insertBallRecod(recodes);
            boolean flag3 = ballService.insertBallIn(ballIns);
            if (flag == flag2 && flag2 == flag3 && flag ) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failure";
    }

    @RequestMapping("countPeriod")
    @ResponseBody
    public Ball getBallCount(Integer begin ,Integer end){
        if(Objects.isNull(begin) && Objects.isNull(end)){
            return null;
        }
        Ball list = ballService.getBallCount(begin,end);
        return  list;
    }




}
