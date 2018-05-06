package com.puao.dao;

import com.puao.bean.BallIn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallInMapper {
    public BallIn selectOne(Integer period);

    public List<BallIn> selectAll();

    public Integer insertOrUpdate(List<BallIn> list);
}
