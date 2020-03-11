package com.qf.solr.v2.consumer.service;

import com.github.pagehelper.PageInfo;
import com.qf.dto.TProductDTO;
import com.qf.entity.TProduct;
import com.qf.vo.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
@FeignClient(value = "solr-v2-service")
public interface ISearchService {

    @RequestMapping("/search/initData")
    List<TProductDTO> initDataToSolr();

//    PageInfo<TProductDTO> searchByKeyWord(String keyWord, Integer pageNum, Integer pageSize);

    @RequestMapping("/search/keyWord")
    PageInfo<TProductDTO> searchByKeyWord(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize,@RequestParam(value = "keyWord") String keyWord);
}
