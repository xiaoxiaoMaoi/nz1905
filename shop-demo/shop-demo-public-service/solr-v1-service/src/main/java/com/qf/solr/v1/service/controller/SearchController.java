package com.qf.solr.v1.service.controller;

import com.qf.dto.ResultBean;
import com.qf.solr.v1.service.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("query")
    public ResultBean searchByKeyword(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String keyword){
        return searchService.searchByKeyword(pageNum,pageSize,keyword);
    }

    @RequestMapping("addProduct")
    public ResultBean addProduct(Long pid){
        return searchService.addProduct(pid);
    }

    @RequestMapping("init")
    public ResultBean initSolr(){
        return searchService.initSolr();
    }
}
