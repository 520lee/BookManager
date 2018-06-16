package com.jcut.service;

import com.github.pagehelper.PageInfo;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.Book;
import com.jcut.vo.BookDetailVo;

public interface IBookService {

    public ServerResponse saveOrUpdateBook(Book book);

    public ServerResponse<String> setBookStatus(String bookId, Integer status);

//    public ServerResponse<BookDetailVo> manageBookDetail(String bookId);

    public ServerResponse<PageInfo> getBookList(int pageNum, int pageSize);

    public ServerResponse<PageInfo> searchBook(String name, String bookId, int pageNum, int pageSize);

    public ServerResponse<BookDetailVo> getBookDetail(String bookId);

    public ServerResponse<PageInfo> getBookByName(String name, int pageNum, int pageSize, String orderBy);

    public ServerResponse deleteBook(String bookId);

    public ServerResponse updateBook(String bookId, Book book);
}
