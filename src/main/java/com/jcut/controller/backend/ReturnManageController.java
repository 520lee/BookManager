package com.jcut.controller.backend;

import com.github.pagehelper.PageInfo;
import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.User;
import com.jcut.service.IReturnService;
import com.jcut.service.IUserService;
import com.jcut.vo.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/return/")
public class ReturnManageController {

    @Autowired
    IReturnService iReturnService;

    @Autowired
    IUserService iUserService;

    @RequestMapping("create.do")
    @ResponseBody
    public ServerResponse createReturn(HttpSession session, String bookId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iReturnService.createManage(bookId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo<ReturnVo>> listReturn(HttpSession session, String userId, String startDate, String endDate, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iReturnService.list(userId, startDate, endDate, pageNum, pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }
}
