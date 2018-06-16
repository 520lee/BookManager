package com.jcut.service;

import com.jcut.common.ServerResponse;
import com.jcut.vo.BorrowVo;

import java.util.List;


public interface IBorrowService {

    public ServerResponse create(String userId, String bookId);

    public ServerResponse renew(String userId, String bookId);

    public ServerResponse<List<BorrowVo>> list(String userId);

}
