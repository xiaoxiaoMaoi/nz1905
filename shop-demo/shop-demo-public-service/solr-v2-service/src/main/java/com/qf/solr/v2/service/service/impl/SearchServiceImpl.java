package com.qf.solr.v2.service.service.impl;

import com.github.pagehelper.PageInfo;
import com.qf.dto.TProductDTO;
import com.qf.execption.NZExecption;
import com.qf.mapper.TProductDTOMapper;
import com.qf.mapper.TProductMapper;
import com.qf.solr.v2.service.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
@Service
@Slf4j
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private TProductDTOMapper productDTOMapper;

    @Override
    public List<TProductDTO> initDataToSolr() {
        List<TProductDTO> productDTOList=productDTOMapper.getAll();

        List<SolrInputDocument> solrInputDocumentList=new ArrayList<>();

        for (TProductDTO tproductDTO : productDTOList) {

            SolrInputDocument solrInputDocument=new SolrInputDocument();

            solrInputDocument.setField("id",tproductDTO.getPid());
            solrInputDocument.setField("t_product_name",tproductDTO.getPname());
            solrInputDocument.setField("t_product_sale_price", tproductDTO.getSalePrice());
            solrInputDocument.setField("t_product_pimage",tproductDTO.getPimage());
            solrInputDocument.setField("t_product_pdesc",tproductDTO.getPdesc());

            solrInputDocumentList.add(solrInputDocument);
        }

        try {
            solrClient.add(solrInputDocumentList);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【全量复制至solr索引库失败】 全量复制至solr索引库失败!! productDTOList = {}",productDTOList);
            throw new NZExecption(200,"全量复制至solr索引库失败");
        }

        return productDTOList;
    }

    @Override
    public PageInfo<TProductDTO> searchByKeyWord(Integer pageNum,Integer pageSize,String keyWord) {
        SolrQuery solrQuery=new SolrQuery();
        if (!StringUtils.isEmpty(keyWord)){
            solrQuery.setQuery(keyWord);
        }else {
            solrQuery.setQuery("手机");
        }
        solrQuery.set("df","t_item_keywords");
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("t_product_name");
        solrQuery.addHighlightField("t_product_pdesc");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");

        solrQuery.setStart((pageNum-1)*pageSize);
        solrQuery.setRows(pageSize);
        List<TProductDTO> productDTOList=new ArrayList<>();
        long totalCount=0L;
        PageInfo<TProductDTO> pageInfo= new PageInfo<>();
        try {
            QueryResponse queryResponse=solrClient.query(solrQuery);

            SolrDocumentList results = queryResponse.getResults();

            totalCount=results.getNumFound();

            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            for (SolrDocument result : results) {
                TProductDTO productDTO=new TProductDTO();
                productDTO.setPid(Long.parseLong(result.getFieldValue("id").toString()));
                productDTO.setSalePrice((Double) result.getFieldValue("t_product_sale_price"));
                productDTO.setPimage(result.getFieldValue("t_product_pimage").toString());
                Map<String, List<String>> map = highlighting.get(result.getFieldValue("id").toString());
                List<String> t_product_name = map.get("t_product_name");
                if (t_product_name!=null&&t_product_name.size()>0){
                    productDTO.setPname(t_product_name.get(0));
                }else {
                    productDTO.setPname(result.getFieldValue("t_product_name").toString());
                }
                List<String> t_product_pdesc = map.get("t_product_pdesc");
                if (t_product_pdesc.size()>0&&t_product_pdesc!=null){
                    productDTO.setPdesc(t_product_pdesc.get(0));
                }else {
                    productDTO.setPdesc(result.getFieldValue("t_product_pdesc").toString());
                }

                productDTOList.add(productDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(totalCount);
        pageInfo.setPages((int) (totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize)+1));
        pageInfo.setList(productDTOList);
        pageInfo.setNavigatePages(5);


        return pageInfo;
    }
}
