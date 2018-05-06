package com.puao.service;

import com.puao.bean.Ball;
import com.puao.bean.BallIn;
import com.puao.bean.BallRecod;

import java.util.List;

public interface IBallService {

    public Integer getMaxPeriod();


    public List<Ball> getBallInfo();

    public List<BallRecod> getBallRecod();

    public boolean insertBallInfo(List<Ball> list);

    public boolean insertBallRecod(List<BallRecod> list);

    public boolean insertBallIn(List<BallIn> list);

    public Ball getBallCount(Integer begin , Integer end);

}
