package com.qf.back.v2.service.service;

import com.qf.dto.ResultBean;

public interface IBackService {

    ResultBean login(String username,String password);
}
