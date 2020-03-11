package com.qf.solr.v1.consumer.service;

import com.qf.dto.ResultBean;
import com.qf.solr.v1.consumer.service.fallback.SearchServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "solr-v1-service",fallback = SearchServiceHystrix.class)
public interface ISearchService {

    ResultBean searchByKeyword(String keyword);

    ResultBean addProduct(Long id);
}
