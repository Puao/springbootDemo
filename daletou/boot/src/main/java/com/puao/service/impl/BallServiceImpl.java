package com.puao.service.impl;

import com.puao.bean.Ball;
import com.puao.bean.BallIn;
import com.puao.bean.BallRecod;
import com.puao.dao.BallInMapper;
import com.puao.dao.BallMapper;
import com.puao.dao.BallRecodMapper;
import com.puao.service.IBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component("ballService")
public class BallServiceImpl implements IBallService {
    @Autowired
    private BallMapper ballMapper;

    @Autowired
    private BallRecodMapper recodMapper;

    @Autowired
    private BallInMapper ballInMapper;
    @Override
    public Integer getMaxPeriod() {

        return ballMapper.selectMaxPeriod();
    }

    @Override
    public List<Ball> getBallInfo() {
        List<Ball> list = ballMapper.selectAll();
        return list;
    }

    @Override
    public List<BallRecod> getBallRecod() {
        return recodMapper.selectAll();
    }

    @Override
    public boolean insertBallInfo(List<Ball> list) {
        boolean flag = false;
        Integer max = ballMapper.selectMaxPeriod();
        if(CollectionUtils.isEmpty(list)){
            return flag;
        }
        max = null;
        Integer period = list.get(list.size()-1).getPeriod();
        if(Objects.isNull(period)){
            return false;
        }
        if(Objects.isNull(max) || max < period){
            Integer count = ballMapper.insertOrUpdate(list);
            if(Objects.nonNull(count) && count > 0){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean insertBallRecod(List<BallRecod> list) {
        Integer count = recodMapper.insertOrUpdate(list);
        if(Objects.nonNull(count) && count > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean insertBallIn(List<BallIn> list) {
        Integer count = ballInMapper.insertOrUpdate(list);
        if(Objects.nonNull(count) && count > 0){
            return true;
        }
        return false;
    }

    @Override
    public Ball getBallCount(Integer begin, Integer end) {
        return ballMapper.countByPeriod(begin,end);
    }
}
