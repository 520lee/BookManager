package com.jcut.dao;

import com.jcut.pojo.Borrow;

import java.util.List;

public interface BorrowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    int selectByBookId(String bookId);

    int selectByUserId(String userId);

    Borrow getByBookId(String bookId);

    List<Borrow> getByUserId(String userId);

    int deleteByBookId(String bookId);
}