package com.jcut.dao;

import com.jcut.pojo.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sort record);

    int insertSelective(Sort record);

    Sort selectByPrimaryKey(Integer sortId);

    int updateByPrimaryKeySelective(Sort record);

    int updateByPrimaryKey(Sort record);

    int updateBySortId(@Param("sortId") Integer sortId, @Param("name") String name);

    List<Sort> selectAllSort();
}