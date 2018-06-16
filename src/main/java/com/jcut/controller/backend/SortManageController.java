package com.jcut.controller.backend;

import com.github.pagehelper.PageInfo;
import com.jcut.common.Const;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.User;
import com.jcut.service.ISortService;
import com.jcut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/sort/")
public class SortManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISortService iSortService;

    @RequestMapping("add_sort.do")
    @ResponseBody
    public ServerResponse addSort(HttpSession session, String name, int sortId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        //校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
        //是管理员，处理分类逻辑
            return iSortService.addSort(name, sortId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("set_sort_name.do")
    @ResponseBody
    public ServerResponse setSortName(HttpSession session, Integer sortId, String name){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        //校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员，更新categoryName
            return iSortService.updateSortName(sortId, name);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_sort.do")
    @ResponseBody
    public ServerResponse getSort (HttpSession session, @RequestParam(value = "sortId", defaultValue = "1") Integer sortId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        //校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员，查询子节点的category信息，并且不递归，保证平级
            return iSortService.getSortById(sortId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_all_sort.do")
    @ResponseBody
    public ServerResponse<PageInfo> getAllSort(HttpSession session,
                                               @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        //校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员，查询当前节点的id和递归子节点的id
            return iSortService.getAllSort(pageNum, pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }
}
