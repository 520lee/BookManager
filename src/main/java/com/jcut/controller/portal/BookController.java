package com.jcut.controller.portal;

import com.github.pagehelper.PageInfo;
import com.jcut.common.ServerResponse;
import com.jcut.service.IBookService;
import com.jcut.vo.BookDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/book/")
public class BookController {

    @Autowired
    private IBookService iBookService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<BookDetailVo> detail(String bookId){
        return iBookService.getBookDetail(bookId);
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> search
    (@RequestParam(value = "name",required = false)
                     String name,
     @RequestParam(value = "pageNum",defaultValue = "1")
                     int pageNum,
     @RequestParam(value = "pageSize",defaultValue = "10")
                     int pageSize,
     @RequestParam(value = "orderBy",defaultValue = "")
                     String orderBy){
        return iBookService.getBookByName(
                name, pageNum, pageSize, orderBy);
    }
}
