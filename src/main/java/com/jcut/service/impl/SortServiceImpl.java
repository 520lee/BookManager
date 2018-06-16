package com.jcut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.jcut.common.ResponseCode;
import com.jcut.common.ServerResponse;
import com.jcut.dao.SortMapper;
import com.jcut.pojo.Sort;
import com.jcut.service.ISortService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iSortService")
public class SortServiceImpl implements ISortService {

    @Autowired
    private SortMapper sortMapper;

    public ServerResponse addSort(String name, Integer sortId){
        if (sortId == null || StringUtils.isBlank(name)){
            return ServerResponse.createByErrorMessage("添加参数错误");
        }
        Sort sort = new Sort();
        sort.setName(name);
        sort.setSortId(sortId);

        int rowCount = sortMapper.insert(sort);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    public ServerResponse updateSortName(Integer sortId, String name){
        if (sortId == null || StringUtils.isBlank(name)){
            return ServerResponse.createByErrorMessage("更新参数错误");
        }

        int rowCount = sortMapper.updateBySortId(sortId,name);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    public ServerResponse<Sort> getSortById(Integer sortId){
        if (sortId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Sort sort = sortMapper.selectByPrimaryKey(sortId);
        if (sort == null){
            return ServerResponse.createByErrorMessage("已删除");
        }
        return ServerResponse.createBySuccess(sort);
    }

    public ServerResponse<PageInfo> getAllSort(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Sort> sortListVoList = Lists.newArrayList();
        List<Sort> sortList = sortMapper.selectAllSort();
        for (Sort sortItem: sortList) {
            sortListVoList.add(sortItem);
        }
        PageInfo pageInfo = new PageInfo(sortList);
        pageInfo.setList(sortListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
