package com.jcut.dao;

import com.jcut.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(String bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectList();

    List<Book> selectByName(String title);

    int updateStatus(@Param("bookId") String bookId, @Param("status") int status);
}