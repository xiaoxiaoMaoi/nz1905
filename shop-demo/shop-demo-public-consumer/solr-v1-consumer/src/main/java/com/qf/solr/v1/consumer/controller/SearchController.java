package com.qf.solr.v1.consumer.controller;

import com.qf.dto.ResultBean;
import com.qf.solr.v1.consumer.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ISearchService service;

    @RequestMapping("query")
    @ResponseBody
    public String searchByKeyword(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam String keyword, ModelMap map){
        ResultBean resultBean = service.searchByKeyword(pageNum,pageSize,keyword);
        //把集合存进去，在resultBean中
        map.put("pageInfo",resultBean.getData());
        System.out.println(resultBean.getData());
        return "search";
    }

    @RequestMapping("addProduct")
    public ResultBean addProduct(Long pid){
        return service.addProduct(pid);
    }

    @RequestMapping("init")
    @ResponseBody
    public ResultBean initSolr(){
        return service.initSolr();
    }
}
