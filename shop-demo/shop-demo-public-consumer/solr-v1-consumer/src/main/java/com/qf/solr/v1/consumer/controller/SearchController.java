package com.qf.solr.v1.consumer.controller;

import com.qf.dto.ResultBean;
import com.qf.solr.v1.consumer.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ISearchService service;

    @RequestMapping("")
    public String searchByKeyword(String keyword){
        ResultBean resultBean = service.searchByKeyword(keyword);
        return "";
    }
}
