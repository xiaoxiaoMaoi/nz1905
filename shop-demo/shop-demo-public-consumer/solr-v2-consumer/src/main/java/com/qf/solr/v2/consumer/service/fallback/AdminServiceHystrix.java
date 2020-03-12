package com.qf.solr.v2.consumer.service.fallback;

import com.qf.dto.TProductDTO;
import com.qf.solr.v2.consumer.service.ISearchService;
import com.qf.vo.ResultBean;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/12
 */
public class AdminServiceHystrix implements ISearchService {
    @Override
    public ResultBean initDataToSolr() {
        return new ResultBean(200,"sorry,您的网络有问题",null);
    }

    @Override
    public ResultBean searchByKeyWord(Integer pageNum, Integer pageSize, String keyWord) {
        return new ResultBean(200,"sorry,您的网络有问题",null);
    }
}
