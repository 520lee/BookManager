package com.jcut.dao;

import com.jcut.pojo.Return;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    List<Return> getByUserId(@Param("userId") String userId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}