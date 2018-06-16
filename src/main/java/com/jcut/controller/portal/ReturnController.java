package com.jcut.controller.portal;

import com.github.pagehelper.PageInfo;
import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.User;
import com.jcut.service.IReturnService;
import com.jcut.vo.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/return/")
public class ReturnController {

    @Autowired
    IReturnService iReturnService;

    @RequestMapping("create.do")
    @ResponseBody
    public ServerResponse createReturn(HttpSession session,
                                       String bookId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReturnService.create(user.getUsername(),bookId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo<ReturnVo>> listReturn(HttpSession session, String startDate, String endDate, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReturnService.list(user.getUsername(), startDate, endDate, pageNum, pageSize);
    }
}
