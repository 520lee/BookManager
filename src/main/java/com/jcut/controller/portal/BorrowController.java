package com.jcut.controller.portal;

import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.User;
import com.jcut.service.IBorrowService;
import com.jcut.vo.BorrowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/borrow/")
public class BorrowController {

    @Autowired
    private IBorrowService iBorrowService;

    @RequestMapping("create.do")
    @ResponseBody
    public ServerResponse createBorrow(HttpSession session,
                                       String bookId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iBorrowService.create(user.getUsername(),bookId);
    }

    @RequestMapping("renew.do")
    @ResponseBody
    public ServerResponse createRenew(HttpSession session,
                                       String bookId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iBorrowService.renew(user.getUsername(),bookId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<List<BorrowVo>> listBorrow(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iBorrowService.list(user.getUsername());
    }
}
