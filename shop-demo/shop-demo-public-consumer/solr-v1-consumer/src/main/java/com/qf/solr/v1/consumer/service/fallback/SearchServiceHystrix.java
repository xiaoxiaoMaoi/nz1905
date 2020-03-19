package com.qf.solr.v1.consumer.service.fallback;

import com.qf.dto.ResultBean;
import com.qf.solr.v1.consumer.service.ISearchService;
import org.springframework.stereotype.Component;

@Component
public class SearchServiceHystrix implements ISearchService {


    @Override
    public ResultBean searchByKeyword(Integer pageNum, Integer pageSize, String keyword) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean addProduct(Long id) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean initSolr() {
        return ResultBean.error("抱歉，您的网络有问题");
    }
}
