package com.qf.back.v2.consumer.controller;

import com.qf.back.v2.consumer.service.IBackService;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("back")
public class BackController {

    @Autowired
    private IBackService service;

    @RequestMapping("login")
    public String login(String username,String password){
        return null;
    }

    @RequestMapping("orderList")
    public String orderList(String account){
        return null;
    }

    @RequestMapping("updateOrderInit")
    public String updateOrderInit(Integer id, ModelMap map){
        ResultBean resultBean = service.updateOrderInit(id);
        map.put("order",resultBean.getData());
        return "updateOrder";
    }

    @RequestMapping("updateOrderById")
    @ResponseBody
    public ResultBean updateOrderById(TOrder order){
        ResultBean resultBean = service.updateOrderById(order);
        return resultBean;
    }

    @RequestMapping("addOrder")
    @ResponseBody
    public ResultBean addOrder(TOrder order){
        ResultBean resultBean = service.addOrder(order);
        return resultBean;
    }

    @RequestMapping("deleteOrderById")
    @ResponseBody
    public ResultBean deleteOrderById(Integer id){
        return service.deleteOrderById(id);
    }

    @RequestMapping("productList")
    public String productList(String account){
        return null;
    }

    @RequestMapping("updateProductInit")
    public String updateProductInit(Integer id,ModelMap map){
        ResultBean resultBean = service.updateProductInit(id);
        map.put("product",resultBean.getData());
        return "updateProduct";
    }

    @RequestMapping("updateProductById")
    @ResponseBody
    public ResultBean updateProductById(TProduct product){
        return service.updateProductById(product);
    }

    @RequestMapping("addProduct")
    @ResponseBody
    public ResultBean addProduct(TProduct product){
        return service.addProduct(product);
    }

    @RequestMapping("deleteProductById")
    @ResponseBody
    public ResultBean deleteProductById(Integer id){
        return service.deleteProductById(id);
    }
}
