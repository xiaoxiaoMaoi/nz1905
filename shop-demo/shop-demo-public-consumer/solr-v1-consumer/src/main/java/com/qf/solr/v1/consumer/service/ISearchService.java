package com.qf.solr.v1.consumer.service;

import com.qf.dto.ResultBean;
import com.qf.solr.v1.consumer.service.fallback.SearchServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "solr-v1-service",fallback = SearchServiceHystrix.class)
public interface ISearchService {

    @RequestMapping("search/query")
    ResultBean searchByKeyword(@RequestParam String keyword);

    @RequestMapping("search/addProduct")
    ResultBean addProduct(@RequestParam Long id);

    @RequestMapping("search/init")
    ResultBean initSolr();
}
