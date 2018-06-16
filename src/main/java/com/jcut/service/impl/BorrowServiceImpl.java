package com.jcut.service.impl;

import com.google.common.collect.Lists;
import com.jcut.common.Const;
import com.jcut.common.ServerResponse;
import com.jcut.dao.BookMapper;
import com.jcut.dao.BorrowMapper;
import com.jcut.pojo.Book;
import com.jcut.pojo.Borrow;
import com.jcut.service.IBorrowService;
import com.jcut.util.DateTimeUtil;
import com.jcut.vo.BorrowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("iBorrowService")
public class BorrowServiceImpl implements IBorrowService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowMapper borrowMapper;


    public ServerResponse create(String userId, String bookId){
        BorrowVo borrowVo = new BorrowVo();
        int resultRow = borrowMapper.selectByUserId(userId);
        if (resultRow < 5){
            if (bookId != ""){
                Book book = bookMapper.selectByPrimaryKey(bookId);
                if (book != null && book.getStatus() == Const.BookStatusEnum.ON_STOCK.getCode()){
                    LocalDateTime borrow_time = LocalDateTime.now();
                    LocalDateTime expect_return_time = borrow_time.plusMonths(1);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtil.STANDARD_FORMAT);
                    Borrow borrow = new Borrow();
                    borrow.setUserId(userId);
                    borrow.setBookId(bookId);
                    borrow.setTitle(book.getTitle());
                    System.out.println(borrow_time.format(formatter));
                    borrow.setBorrowTime(DateTimeUtil.strToDate(borrow_time.format(formatter)));
                    borrow.setExpectReturnTime(DateTimeUtil.strToDate(expect_return_time.format(formatter)));
                    borrowVo.setBookId(book.getBookId());
                    borrowVo.setTitle(book.getTitle());
                    borrowVo.setAuthor(book.getAuthor());
                    borrowVo.setPublish(book.getPublish());
                    borrowVo.setPrice(book.getPrice().toString());
                    borrowVo.setBorrowTime(DateTimeUtil.dateToStr(borrow.getBorrowTime()));
                    borrowVo.setExpectReturnTime(DateTimeUtil.dateToStr(borrow.getExpectReturnTime()));
                    int rowCount = borrowMapper.insert(borrow);
                    if (rowCount > 0){
                        bookMapper.updateStatus(bookId, Const.BookStatusEnum.ON_BORROW.getCode());
                        borrowVo.setStatus("借出");
                        return ServerResponse.createBySuccess("借阅成功", borrowVo);
                    }
                }
            }
        }
            return ServerResponse.createByErrorMessage("借阅失败");
    }

    public ServerResponse renew(String userId, String bookId){
        BorrowVo borrowVo = new BorrowVo();
        Borrow borrow = borrowMapper.getByBookId(bookId);
        if (borrow != null && userId.equals(borrow.getUserId())){
            Book book = bookMapper.selectByPrimaryKey(bookId);
            String status = book.getStatus() == 1?"借出":"在库";
            LocalDateTime borrow_time = LocalDateTime.now();
            LocalDateTime expect_return_time = borrow_time.plusMonths(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtil.STANDARD_FORMAT);
            borrow.setBorrowTime(DateTimeUtil.strToDate(borrow_time.format(formatter)));
            borrow.setExpectReturnTime(DateTimeUtil.strToDate(expect_return_time.format(formatter)));
            borrowVo.setBookId(book.getBookId());
            borrowVo.setTitle(book.getTitle());
            borrowVo.setAuthor(book.getAuthor());
            borrowVo.setPublish(book.getPublish());
            borrowVo.setPrice(book.getPrice().toString());
            borrowVo.setStatus(status);
            borrowVo.setBorrowTime(DateTimeUtil.dateToStr(borrow.getBorrowTime()));
            borrowVo.setExpectReturnTime(DateTimeUtil.dateToStr(borrow.getExpectReturnTime()));
            int rowCount = borrowMapper.updateByPrimaryKeySelective(borrow);
            if (rowCount > 0){
//                bookMapper.updateStatus(bookId, Const.BookStatusEnum.ON_BORROW.getCode());
                return ServerResponse.createBySuccess("续借成功", borrowVo);
            }
        }
        return ServerResponse.createByErrorMessage("续借失败");
    }

    public ServerResponse<List<BorrowVo>> list(String userId){
        List<BorrowVo> borrowVoList = Lists.newArrayList();
        List<Borrow> borrowList = borrowMapper.getByUserId(userId);
        if (borrowList != null){
            for (Borrow borrowItem: borrowList){
                Book book = bookMapper.selectByPrimaryKey(borrowItem.getBookId());
                BorrowVo borrowVo = assembleBookListVo(borrowItem, book);
                borrowVoList.add(borrowVo);
            }
            return ServerResponse.createBySuccess("查询成功", borrowVoList);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    private BorrowVo assembleBookListVo(Borrow borrow, Book book){
        String status = book.getStatus() == 1?"借出":"在库";
        BorrowVo borrowVo = new BorrowVo();
        borrowVo.setBookId(book.getBookId());
        borrowVo.setTitle(book.getTitle());
        borrowVo.setAuthor(book.getAuthor());
        borrowVo.setPublish(book.getPublish());
        borrowVo.setPrice(book.getPrice().toString());
        borrowVo.setStatus(status);
        borrowVo.setBorrowTime(DateTimeUtil.dateToStr(borrow.getBorrowTime()));
        borrowVo.setExpectReturnTime(DateTimeUtil.dateToStr(borrow.getExpectReturnTime()));
        return borrowVo;
    }
}
