package com.qf.back.v2.service.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;
import com.qf.back.v2.service.service.IBackService;
import com.qf.dto.PageBean;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("back")
public class BackController {

    @Autowired
    private IBackService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @RequestMapping("login")
    @ResponseBody
    public ResultBean login(String username, String password){
        return service.login(username,password);
    }

    @RequestMapping("orderList")
    @ResponseBody
    public PageInfo<TOrder> orderList(PageBean pageBean, String account){
        return service.orderList(pageBean,account);
    }

    @RequestMapping("updateOrderInit")
    @ResponseBody
    public ResultBean updateOrderInit(Integer id){
        return service.updateOrderInit(id);
    }

    @RequestMapping("updateOrderById")
    @ResponseBody
    public ResultBean updateOrderById(@RequestBody @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") TOrder order){
        ResultBean resultBean = service.updateOrderById(order);
        return resultBean;
    }

    @RequestMapping("addOrder")
    @ResponseBody
    public ResultBean addOrder(@RequestBody @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")TOrder order){
        ResultBean resultBean = service.addOrder(order);
        return resultBean;
    }

    @RequestMapping("deleteOrderById")
    @ResponseBody
    public ResultBean deleteOrderById(Integer id){
        return service.deleteOrderById(id);
    }

    @RequestMapping("productList")
    @ResponseBody
    public PageInfo<TProduct> productList(PageBean pageBean,String pname,Integer typeId){
        return service.productList(pageBean,pname,typeId);
    }

    @RequestMapping("updateProductInit")
    @ResponseBody
    public ResultBean updateProductInit(Integer id){
        return service.updateProductInit(id);
    }

    @RequestMapping("updateProductById")
    @ResponseBody
    public ResultBean updateProductById(@RequestBody @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") TProduct product){
        return service.updateProductById(product);
    }

    @RequestMapping("addProduct")
    @ResponseBody
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    public ResultBean addProduct(@RequestBody @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")TProduct product){
        return service.addProduct(product);
    }

    @RequestMapping("deleteProductById")
    @ResponseBody
    public ResultBean deleteProductById(Integer id){
        return service.deleteProductById(id);
    }

    @RequestMapping("selectProductType")
    @ResponseBody
    public ResultBean selectProductType(){
        return service.selectProductType();
    }
}
