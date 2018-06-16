package com.jcut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.jcut.common.Const;
import com.jcut.common.ServerResponse;
import com.jcut.dao.BookMapper;
import com.jcut.dao.BorrowMapper;
import com.jcut.dao.ReturnMapper;
import com.jcut.pojo.Book;
import com.jcut.pojo.Borrow;
import com.jcut.pojo.Return;
import com.jcut.service.IReturnService;
import com.jcut.util.DateTimeUtil;
import com.jcut.vo.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service("iReturnService")
public class ReturnServiceImpl implements IReturnService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private ReturnMapper returnMapper;

    public ServerResponse create(String userId, String bookId){
        ReturnVo returnVo = new ReturnVo();
        int resultRow = borrowMapper.selectByUserId(userId);
        if (resultRow > 0 && bookId != ""){
            Borrow borrow = borrowMapper.getByBookId(bookId);
            Book book = bookMapper.selectByPrimaryKey(bookId);
            if (borrow != null){
                borrowMapper.deleteByBookId(bookId);
                LocalDateTime return_time = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtil.STANDARD_FORMAT);
                Date borrow_time = borrow.getBorrowTime();
                Return returnTab = new Return();
                returnTab.setUserId(userId);
                returnTab.setBookId(bookId);
                returnTab.setTitle(book.getTitle());
                returnTab.setBorrowTime(borrow_time);
                returnTab.setReturnTime(DateTimeUtil.strToDate(return_time.format(formatter)));
                returnVo.setBookId(bookId);
                returnVo.setTitle(book.getTitle());
                returnVo.setAuthor(book.getAuthor());
                returnVo.setPublish(book.getPublish());
                returnVo.setPrice(book.getPrice().toString());
                returnVo.setBorrowTime(DateTimeUtil.dateToStr(returnTab.getBorrowTime()));
                returnVo.setReturnTime(DateTimeUtil.dateToStr(returnTab.getReturnTime()));
                int rowCount = returnMapper.insert(returnTab);
                if (rowCount > 0){
                    bookMapper.updateStatus(bookId, Const.BookStatusEnum.ON_STOCK.getCode());
                    returnVo.setStatus("在库");
                    return ServerResponse.createBySuccess("归还成功", returnVo);
                }
            }
        }
        return ServerResponse.createByErrorMessage("归还失败");
    }

    public ServerResponse createManage(String bookId){
        ReturnVo returnVo = new ReturnVo();
            Borrow borrow = borrowMapper.getByBookId(bookId);
            Book book = bookMapper.selectByPrimaryKey(bookId);
            if (borrow != null){
                borrowMapper.deleteByBookId(bookId);
                LocalDateTime return_time = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtil.STANDARD_FORMAT);
                Date borrow_time = borrow.getBorrowTime();
                Return returnTab = new Return();
                returnTab.setUserId(borrow.getUserId());
                returnTab.setBookId(bookId);
                returnTab.setTitle(book.getTitle());
                returnTab.setBorrowTime(borrow_time);
                returnTab.setReturnTime(DateTimeUtil.strToDate(return_time.format(formatter)));
                returnVo.setBookId(bookId);
                returnVo.setTitle(book.getTitle());
                returnVo.setAuthor(book.getAuthor());
                returnVo.setPublish(book.getPublish());
                returnVo.setPrice(book.getPrice().toString());
                returnVo.setBorrowTime(DateTimeUtil.dateToStr(returnTab.getBorrowTime()));
                returnVo.setReturnTime(DateTimeUtil.dateToStr(returnTab.getReturnTime()));
                int rowCount = returnMapper.insert(returnTab);
                if (rowCount > 0){
                    bookMapper.updateStatus(bookId, Const.BookStatusEnum.ON_STOCK.getCode());
                    returnVo.setStatus("在库");
                    return ServerResponse.createBySuccess("归还成功", returnVo);
                }
            }
        return ServerResponse.createByErrorMessage("归还失败");
    }

    public ServerResponse<PageInfo<ReturnVo>> list(String userId, String startDate, String endDate, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize, "borrow_time desc");
        List<ReturnVo> returnVoList = Lists.newArrayList();
        List<Return> returnList = returnMapper.getByUserId(userId, startDate, endDate);
        if (returnList != null){
            for (Return returnItem: returnList){
                Book book = bookMapper.selectByPrimaryKey(returnItem.getBookId());
                ReturnVo returnVo = assembleReturnListVo(returnItem, book);
                returnVoList.add(returnVo);
            }
            PageInfo pageResult = new PageInfo(returnList);
            pageResult.setList(returnVoList);
            return ServerResponse.createBySuccess("查询成功", pageResult);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    private ReturnVo assembleReturnListVo(Return returns, Book book){
        String status = book.getStatus() == 1?"借出":"在库";
        ReturnVo returnVo = new ReturnVo();
        returnVo.setBookId(book.getBookId());
        returnVo.setTitle(book.getTitle());
        returnVo.setAuthor(book.getAuthor());
        returnVo.setPublish(book.getPublish());
        returnVo.setPrice(book.getPrice().toString());
        returnVo.setStatus(status);
        returnVo.setBorrowTime(DateTimeUtil.dateToStr(returns.getBorrowTime()));
        returnVo.setReturnTime(DateTimeUtil.dateToStr(returns.getReturnTime()));
        return returnVo;
    }
}
