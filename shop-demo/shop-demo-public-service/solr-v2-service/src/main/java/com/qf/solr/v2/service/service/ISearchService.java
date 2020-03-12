package com.qf.solr.v2.service.service;

import com.github.pagehelper.PageInfo;
import com.qf.dto.TProductDTO;
import com.qf.entity.TProduct;
import com.qf.vo.ResultBean;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
public interface ISearchService {
    List<TProductDTO> initDataToSolr();

    ResultBean searchByKeyWord(Integer pageNum,Integer pageSize,String keyWord);
}
