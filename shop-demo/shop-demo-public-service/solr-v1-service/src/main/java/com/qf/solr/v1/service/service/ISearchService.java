package com.qf.solr.v1.service.service;

import com.qf.dto.ResultBean;

public interface ISearchService {

    ResultBean searchByKeyword(Integer pageNum,Integer pageSize,String keyword);

    ResultBean addProduct(Long id);

    ResultBean initSolr();
}
