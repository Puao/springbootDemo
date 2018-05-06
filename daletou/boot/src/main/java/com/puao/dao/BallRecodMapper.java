package com.puao.dao;

import com.puao.bean.BallRecod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallRecodMapper {

    public BallRecod selectOne(Integer period);

    public List<BallRecod> selectAll();

    public Integer insertOrUpdate(List<BallRecod> list);
}
