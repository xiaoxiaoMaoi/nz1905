package com.qf.back.v2.service.service;

import com.github.pagehelper.PageInfo;
import com.qf.dto.PageBean;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;

public interface IBackService {

    ResultBean login(String username,String password);

    //-------------以下是订单的操作------------------

    PageInfo<TOrder> orderList(PageBean pageBean, String account);

    ResultBean updateOrderInit(Integer id);

    ResultBean updateOrderById(TOrder order);

    ResultBean addOrder(TOrder order);

    ResultBean deleteOrderById(Integer id);

    //-------------以下是商品的操作------------------

    PageInfo<TProduct> productList(PageBean pageBean,String pname,Integer typeId);

    ResultBean updateProductInit(Integer id);

    ResultBean updateProductById(TProduct tProduct);

    ResultBean addProduct(TProduct product);

    ResultBean addProductInit(Integer id);

    ResultBean deleteProductById(Integer id);

    ResultBean selectProductType();
}
