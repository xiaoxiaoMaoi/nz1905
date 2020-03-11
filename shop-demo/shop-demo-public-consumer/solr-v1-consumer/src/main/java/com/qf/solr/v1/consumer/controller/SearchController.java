package com.qf.solr.v1.consumer.controller;

import com.qf.dto.ResultBean;
import com.qf.solr.v1.consumer.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ISearchService service;

    @RequestMapping("query")
    @ResponseBody
    public String searchByKeyword(String keyword, ModelMap map){
        ResultBean resultBean = service.searchByKeyword(keyword);
        //把集合存进去，在resultBean中
        map.put("products",resultBean.getData());
        System.out.println(resultBean.getData());
        return "search";
    }

    @RequestMapping("addProduct")
    public ResultBean addProduct(Long pid){
        return service.addProduct(pid);
    }

    @RequestMapping("init")
    public ResultBean initSolr(){
        return service.initSolr();
    }
}
