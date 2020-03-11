package com.qf.solr.v1.service.service;

import com.qf.dto.ResultBean;

public interface ISearchService {

    ResultBean searchByKeyword(String keyword);

    ResultBean addProduct(Long id);
}
