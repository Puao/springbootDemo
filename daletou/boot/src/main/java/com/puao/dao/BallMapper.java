package com.puao.dao;

import com.puao.bean.Ball;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallMapper {
    public Ball selectOne(Ball ball);

    public List<Ball> selectAll();

    public Integer insertOrUpdate(List<Ball> list);

    public Integer selectMaxPeriod();

    public Ball countByPeriod(@Param("begin")Integer begin, @Param("end") Integer end);
}
