package com.puao.util;


import com.puao.bean.Ball;
import com.puao.bean.BallIn;
import com.puao.bean.BallRecod;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ArrayStack;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsoupUtils {

//    public static void main(String[] args) throws Exception {
//
////        String url = "http://trend.caipiao.163.com/dlt/?beginPeriod=07002&endPeriod=18049";
//        String url = "http://trend.caipiao.163.com/dlt/?beginPeriod=07001&endPeriod=07001";
//        Map<String,List> map = getBall(url);
//        System.out.print("");
//    }

    public static Map<String,List> getBall(String url) throws Exception {
        Document doc = Jsoup.connect(url)
                .timeout(8000).userAgent("Mozilla").get();
        Element body = doc.body();
        Element element = body.getElementById("cpdata");
        Elements trs = element.getElementsByTag("tr");

        List<Ball> balllist = new ArrayList<>();
        List<BallRecod> recodlist = new ArrayList<>();
        List<BallIn> ballInList = new ArrayList<>();
        for (Element tr : trs) {
            Ball ball = new Ball();
            BallRecod recode = new BallRecod();
            BallIn ballIn = new BallIn();
            //红色
            Elements red = tr.getElementsByClass("ball_red");
            Elements brown = tr.getElementsByClass("ball_brown");
            red.addAll(brown);
            Elements per = tr.getElementsByTag("td");
            Element period = per.first();
            Integer periodI = Integer.valueOf(period.html());

            ball.setPeriod(periodI);
            recode.setPeriod(periodI);
            ballIn.setPeriod(periodI);
            Integer redBallSum = 0;
            int redCount = 1;

            //奇数
            int odd_r = 0;
            int odd_b = 0;
            //偶数
            int even_r = 0;
            int even_b = 0;
            for (Element ba : red) {
                String num = ba.html();
                String source = "red_" + num;
                redBallSum += Integer.valueOf(num);
                BeanUtils.setProperty(ball,source,1);

                String key = "red_0"+redCount;
                BeanUtils.setProperty(ballIn,key,num);
                redCount ++;

                //计算奇偶比
                int temp = Integer.valueOf(num);
                if(temp % 2 == 0){
                    even_r ++;
                }else{
                    odd_r ++;
                }
            }
            recode.setRed_total(redBallSum);
            recode.setRed_avg(Double.valueOf(redBallSum/5.00d));
            recode.setOdd_even_r(odd_r+":"+even_r);
            //蓝色
            Integer blueBallSum = 0;
            Elements blues = tr.getElementsByClass("ball_blue");
            int blueCount = 1;
            for(Element blue : blues){
                String num = blue.html();
                String source = "blue_" + num;
                blueBallSum += Integer.valueOf(num);
                BeanUtils.setProperty(ball,source,1);

                String key = "blue_0"+blueCount;
                BeanUtils.setProperty(ballIn,key,num);
                blueCount ++;

                //计算奇偶比
                int temp = Integer.valueOf(num);
                if(temp % 2 == 0){
                    even_b ++;
                }else{
                    odd_b ++;
                }
            }
            recode.setBlue_total(blueBallSum);
            recode.setBlue_avg(Double.valueOf(blueBallSum/2.00d));

            recode.setAll_total(redBallSum+blueBallSum);
            recode.setAll_avg(Double.valueOf((redBallSum+blueBallSum)/7.00d));

            recode.setOdd_even_b(odd_b+":"+even_b);

            balllist.add(ball);
            recodlist.add(recode);
            ballInList.add(ballIn);
        }
        Map<String,List> result = new HashMap<>();
        result.put("ball",balllist);
        result.put("recode",recodlist);
        result.put("ballIn",ballInList);
        return result;
    }
}
