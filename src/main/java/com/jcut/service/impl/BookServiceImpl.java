package com.jcut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.dao.BookMapper;
import com.jcut.dao.SortMapper;
import com.jcut.pojo.Book;
import com.jcut.pojo.Sort;
import com.jcut.service.IBookService;
import com.jcut.vo.BookDetailVo;
import com.jcut.vo.BookListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iBookService")
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private SortMapper sortMapper;

    public ServerResponse saveOrUpdateBook(Book book){
        if (book != null){
            String bookId = book.getBookId();
            Book oldBook = bookMapper.selectByPrimaryKey(bookId);
            if (oldBook != null){
                book.setId(oldBook.getId());
                int rowCount = bookMapper.updateByPrimaryKey(book);
                if (rowCount > 0) {
                    Book newBook = bookMapper.selectByPrimaryKey(bookId);
                    return ServerResponse.createBySuccess("更新成功", newBook);
                }
                return ServerResponse.createByErrorMessage("更新失败");
            }else {
                int rowCount = bookMapper.insert(book);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("新增成功", book);
                }
                return ServerResponse.createByErrorMessage("新增失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新参数不正确");
    }

    public ServerResponse<String> setBookStatus(String bookId, Integer status){
        if (bookId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int rowCount = bookMapper.updateStatus(bookId, status);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

//    public ServerResponse<BookDetailVo> manageBookDetail(String bookId){
//        if (bookId == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
//        }
//        Book book = bookMapper.selectByPrimaryKey(bookId);
//        if (book == null){
//            return ServerResponse.createByErrorMessage("已下架或删除");
//        }
//        //vo对象--value object
//        //pojo->bo(business object)->vo(view object)
//        BookDetailVo bookDetailVo = assembleBookDetailVo(book);
//        return ServerResponse.createBySuccess(bookDetailVo);
//    }

    private BookDetailVo assembleBookDetailVo(Book book){
        String status = book.getStatus() == 1?"借出":"在库";
        Sort sort = sortMapper.selectByPrimaryKey(book.getSortId());
        BookDetailVo bookDetailVo = new BookDetailVo();
        bookDetailVo.setBookId(book.getBookId());
        bookDetailVo.setTitle(book.getTitle());
        bookDetailVo.setOriginTitle(book.getOriginTitle());
        bookDetailVo.setAuthor(book.getAuthor());
        bookDetailVo.setAuthorInfo(book.getAuthorInfo());
        bookDetailVo.setPublish(book.getPublish());
        bookDetailVo.setPrice(book.getPrice());
        bookDetailVo.setSummary(book.getSummary());
        bookDetailVo.setStatus(status);
        bookDetailVo.setSort(sort.getName());
        bookDetailVo.setCoverUrl(book.getCoverUrl());
        bookDetailVo.setIsbn(book.getIsbn());
        bookDetailVo.setPubdate(book.getPubdate());
        bookDetailVo.setPages(book.getPages());
        bookDetailVo.setBinding(book.getBinding());
        bookDetailVo.setAverage(book.getAverage());
        bookDetailVo.setNumraters(book.getNumraters());
        return bookDetailVo;
    }

    public ServerResponse<PageInfo> getBookList(int pageNum, int pageSize){
        //startPage -- start
        //填充自己的sql查询逻辑
        //pageHelper -- 收尾
        PageHelper.startPage(pageNum, pageSize);
        List<Book> bookList = bookMapper.selectList();

        List<BookListVo> bookListVoList = Lists.newArrayList();
        for (Book bookItem: bookList) {
            BookListVo bookListVo = assembleBookListVo(bookItem);
            bookListVoList.add(bookListVo);
        }
        PageInfo pageResult = new PageInfo(bookList);
        pageResult.setList(bookListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private BookListVo assembleBookListVo(Book book){
        String status = book.getStatus() == 1?"借出":"在库";
        Sort sort = sortMapper.selectByPrimaryKey(book.getSortId());
        BookListVo bookListVo = new BookListVo();
        bookListVo.setBookId(book.getBookId());
        bookListVo.setName(book.getTitle());
        bookListVo.setAuthor(book.getAuthor());
        bookListVo.setPublish(book.getPublish());
        bookListVo.setPrice(book.getPrice());
        bookListVo.setCoverUrl(book.getCoverUrl());
        bookListVo.setAverage(book.getAverage());
        return bookListVo;
    }

    public ServerResponse<PageInfo> searchBook(String name, String bookId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(name)){
            name = new StringBuilder().append("%").append(name).append("%").toString();
        }
        List<Book> bookList = bookMapper.selectByName(name);
        List<BookListVo> bookListVoList = Lists.newArrayList();
        for (Book bookItem: bookList) {
            BookListVo bookListVo = assembleBookListVo(bookItem);
            bookListVoList.add(bookListVo);
        }
        PageInfo pageResult = new PageInfo(bookList);
        pageResult.setList(bookListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<BookDetailVo> getBookDetail(String bookId){
        if (bookId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Book book = bookMapper.selectByPrimaryKey(bookId);
        if (book == null){
            return ServerResponse.createByErrorMessage("已下架或删除");
        }
//        if (book.getStatus() != Const.BookStatusEnum.ON_SALE.getCode()){
//            return ServerResponse.createByErrorMessage("已下架或删除");
//        }
        //vo对象--value object
        //pojo->bo(business object)->vo(view object)
        BookDetailVo bookDetailVo = assembleBookDetailVo(book);
        return ServerResponse.createBySuccess(bookDetailVo);
    }

    public ServerResponse<PageInfo> getBookByName(String name, int pageNum, int pageSize, String orderBy){
        if (StringUtils.isBlank(name)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if (StringUtils.isNotBlank(name)) {
            name = new StringBuilder().append("%").append(name).append("%").toString();
        }
        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if (StringUtils.isNotBlank(orderBy)){
            if (Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Book> bookList = bookMapper.selectByName(name);
        List<BookListVo> bookListVoList = Lists.newArrayList();
        for (Book bookItem: bookList) {
            BookListVo bookListVo = assembleBookListVo(bookItem);
            bookListVoList.add(bookListVo);
        }
        PageInfo pageInfo = new PageInfo(bookList);
        pageInfo.setList(bookListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse deleteBook(String bookId){
        if (bookId != ""){
            Book book = bookMapper.selectByPrimaryKey(bookId);
            int rowCount = bookMapper.deleteByPrimaryKey(bookId);
            if (rowCount > 0) {
                return ServerResponse.createBySuccess("删除成功", book);
            }
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    public ServerResponse updateBook(String bookId, Book book){
        if (bookId != ""){
            Book oldBook = bookMapper.selectByPrimaryKey(bookId);
            book.setId(oldBook.getId());
            int rowCount = bookMapper.updateByPrimaryKeySelective(book);
            if (rowCount > 0) {
                Book newBook = bookMapper.selectByPrimaryKey(bookId);
                return ServerResponse.createBySuccess("更新成功", newBook);
            }
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }
}
