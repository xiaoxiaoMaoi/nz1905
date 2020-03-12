package com.qf.solr.v2.consumer.controller;

import com.github.pagehelper.PageInfo;
import com.qf.dto.TProductDTO;
import com.qf.entity.TProduct;
import com.qf.solr.v2.consumer.service.ISearchService;
import com.qf.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ISearchService searchService;



    @RequestMapping("initData")
    @ResponseBody
    public ResultBean initDataToSolr(){
        ResultBean resultBean=searchService.initDataToSolr();
        return new ResultBean(0,"全量复制至solr搜引库成功",resultBean.getData());
    }

    @RequestMapping("keyWord/{pageIndex}/{pagesSize}")
    public String searchByKeyWord(@PathVariable String pageIndex, @PathVariable String pagesSize,@RequestParam String keyWord,Model model){
        Integer pageNum=Integer.parseInt(pageIndex);
        Integer pageSize=Integer.parseInt(pagesSize);
        System.out.println(pageNum);
        ResultBean resultBean= searchService.searchByKeyWord(pageNum,pageSize,keyWord);
        model.addAttribute("pageInfo",resultBean.getData());
        System.out.println(resultBean.getData());
        return "search";

    }



}
