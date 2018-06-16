package com.jcut.service;

import com.github.pagehelper.PageInfo;
import com.jcut.common.ServerResponse;
import com.jcut.pojo.Sort;


public interface ISortService {
    public ServerResponse addSort(String name, Integer sortId);

    public ServerResponse updateSortName(Integer sortId, String name);

    public ServerResponse<Sort> getSortById(Integer sortId);

    public ServerResponse<PageInfo> getAllSort(Integer pageNum, Integer pageSize);
}