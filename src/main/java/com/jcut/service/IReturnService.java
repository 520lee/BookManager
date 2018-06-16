package com.jcut.service;

import com.github.pagehelper.PageInfo;
import com.jcut.common.ServerResponse;
import com.jcut.vo.ReturnVo;

public interface IReturnService {

    public ServerResponse create(String userId, String bookId);

    public ServerResponse createManage(String bookId);

    public ServerResponse<PageInfo<ReturnVo>> list(String userId, String startDate, String endDate, int pageNum, int pageSize);
}
