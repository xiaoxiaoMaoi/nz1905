package com.qf.solr.v2.consumer.service;

import com.github.pagehelper.PageInfo;
import com.qf.dto.TProductDTO;
import com.qf.entity.TProduct;
import com.qf.solr.v2.consumer.service.fallback.AdminServiceHystrix;
import com.qf.vo.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
@FeignClient(name = "solr-v2-service",fallback = AdminServiceHystrix.class)
public interface ISearchService {

    @RequestMapping("search/initData")
    ResultBean initDataToSolr();


    @RequestMapping("search/keyWord")
    ResultBean searchByKeyWord(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam("keyWord") String keyWord);
}
