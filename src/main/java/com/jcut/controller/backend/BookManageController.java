package com.jcut.controller.backend;

import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.Book;
import com.jcut.pojo.User;
import com.jcut.service.IBookService;
import com.jcut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/book/")
public class BookManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IBookService iBookService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse bookSave(HttpSession session, Book book){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //增加产品的业务逻辑
            return iBookService.saveOrUpdateBook(book);
        }else {
            return ServerResponse.createByErrorMessage(
                    "无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("set_book_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, String bookId, Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBookService.setBookStatus(bookId, status);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, String bookId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBookService.getBookDetail(bookId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBookService.getBookList(pageNum, pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse bookSearch(HttpSession session,
        String name, String bookId,
        @RequestParam(value = "pageNum", defaultValue = "1")
               int pageNum,
        @RequestParam(value = "pageSize", defaultValue = "10")
               int pageSize){
        User user =
        (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBookService.searchBook(name, bookId,
                    pageNum, pageSize);
        }else {
            return ServerResponse.createByErrorMessage(
                    "无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse bookDelete(HttpSession session,
                                     String bookId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBookService.deleteBook(bookId);
        }else {
            return ServerResponse.createByErrorMessage(
                    "无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse bookUpdate(HttpSession session,
                                     String bookId, Book book){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBookService.updateBook(bookId, book);
        }else {
            return ServerResponse.createByErrorMessage(
                    "无权限操作，需要管理员权限");
        }
    }
}
