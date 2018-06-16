package com.jcut.controller.backend;

import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.User;
import com.jcut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user/")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username,
                                      String password,
                                      HttpSession session){
        ServerResponse<User> response = iUserService.login(
                username, password);
        if (response.isSuccess()){
            User user = response.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN){
                //登录管理员成功
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else {
                return ServerResponse.createByErrorMessage(
                        "不是管理员，无法登录");
            }
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "get_user.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session, String username){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，强制登录status=10");
        }
        return iUserService.getinformation(username);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> delete(
            HttpSession session, String username){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.
                    getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员，处理分类逻辑
            return iUserService.delete(username);
        }else {
            return ServerResponse.createByErrorMessage(
                    "无权限操作，需要管理员权限");
        }
    }
}
