package com.qf.solr.v1.service.service.impl;

import com.github.pagehelper.PageInfo;
import com.qf.dto.ResultBean;
import com.qf.dto.TProductSearchDTO;
import com.qf.mapper.TProductSearchDTOMapper;
import com.qf.solr.v1.service.service.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private TProductSearchDTOMapper mapper;

    @Override
    public ResultBean searchByKeyword(Integer pageNum,Integer pageSize,String keyword) {

        //1.创建查询对象
        SolrQuery query = new SolrQuery();
        query.set("df","t_product_keywords");
        query.setQuery(keyword);
        //2.分页
        query.setStart((pageNum-1)*pageSize);
        query.setRows(pageSize);
        PageInfo<TProductSearchDTO> pageInfo = new PageInfo<>();
        long totalCount = 0L;
        List<TProductSearchDTO> products =new ArrayList<>();
        //3.高亮
        query.setHighlight(true);
        query.addHighlightField("t_product_name");
        query.setHighlightSimplePre("<span style='color:red'>");
        query.setHighlightSimplePost("</span>");

        try {
            QueryResponse response =solrClient.query(query);

            //获得结果集
            SolrDocumentList results =response.getResults();

            totalCount = results.getNumFound();

            //获得高亮结果集
            Map<String,Map<String,List<String>>> highlighting = response.getHighlighting();

            for (SolrDocument document : results) {
                TProductSearchDTO product = new TProductSearchDTO();
                String stringId = (String)document.getFieldValue("id");
                Long id = Long.parseLong(stringId);
                product.setId(id);

                //==========从高亮结果集中那带高亮效果的t_product_name=============
                Map<String,List<String>> stringListMap =highlighting.get(stringId);
                List<String> t_product_nameList =stringListMap.get("t_product_name");
                String t_product_name = t_product_nameList.get(0);
                product.setTProductName(t_product_name);

                Double t_product_sale_price =(Double) document.getFieldValue("t_product_sale_price");
                product.setTProductSalePrice(new BigDecimal(t_product_sale_price));
                String t_product_pimage = (String) document.getFieldValue("t_product_pimage");
                product.setTProductPimage(t_product_pimage);
                String t_product_pdesc = (String) document.getFieldValue("t_product_pdesc");
                product.setTProductPdesc(t_product_pdesc);

                products.add(product);
            }
            pageInfo.setPageNum(pageNum);
            pageInfo.setPageSize(pageSize);
            pageInfo.setTotal(totalCount);
            pageInfo.setPages((int) (totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize)+1));
            pageInfo.setList(products);
            pageInfo.setNavigatePages(5);
            return ResultBean.success(pageInfo);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultBean.error("查询出现异常");
    }

    /**
     *  //1.根据pid从数据库中获取该商品
     *         //2.封装成SolrInputDocument
     *         //3.插入到solr库里
     * @param id
     * @return
     */
    @Override
    public ResultBean addProduct(Long id) {
        //1.根据pid从数据库中获取该商品
        TProductSearchDTO product =mapper.selectById(id);
        //2.封装成SolrInputDocument
        SolrInputDocument document =new SolrInputDocument();
        document.setField("id",product.getId().toString());
        document.setField("t_product_name",product.getTProductName());
        document.setField("t_product_sale_price",product.getTProductSalePrice().floatValue());
        document.setField("t_product_pimage",product.getTProductPimage());
        document.setField("t_product_pdesc",product.getTProductPdesc());
        //3.插入到solr库里
        try {
            solrClient.add(document);
            solrClient.commit();
            return ResultBean.success("插入搜索库成功!");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultBean.error("插入搜索库失败");
    }

    @Override
    public ResultBean initSolr() {
        //从数据库中拿到数据
        List<TProductSearchDTO> products = mapper.selectAll();

        //存放所有的document对象
        List<SolrInputDocument> docs = new ArrayList<>();

        //遍历products集合，将每一个product对象封装成一个SolrInputDocument对象
        for (TProductSearchDTO product : products) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",product.getId().toString());
            document.setField("t_product_name",product.getTProductName());
            document.setField("t_product_sale_price",product.getTProductSalePrice().floatValue());
            document.setField("t_product_pimage",product.getTProductPimage());
            document.setField("t_product_pdesc",product.getTProductPdesc());

            //存到集合中
            docs.add(document);
        }
        //将集合添加到solr库里面
        try {
            solrClient.add(docs);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ResultBean.success("添加到solr库成功!");
    }
}
