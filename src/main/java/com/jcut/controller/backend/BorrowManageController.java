package com.jcut.controller.backend;

import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.User;
import com.jcut.service.IBorrowService;
import com.jcut.service.IUserService;
import com.jcut.vo.BorrowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manage/borrow/")
public class BorrowManageController {

    @Autowired
    private IBorrowService iBorrowService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("create.do")
    @ResponseBody
    public ServerResponse createManageBorrow(HttpSession session, String userId, String bookId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBorrowService.create(userId,bookId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("renew.do")
    @ResponseBody
    public ServerResponse createManageRenew(HttpSession session, String userId, String bookId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBorrowService.renew(userId,bookId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<List<BorrowVo>> listManageBorrow(
            HttpSession session, String userId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iBorrowService.list(userId);
        }else {
            return ServerResponse.createByErrorMessage(
                    "无权限操作，需要管理员权限");
        }
    }
}
