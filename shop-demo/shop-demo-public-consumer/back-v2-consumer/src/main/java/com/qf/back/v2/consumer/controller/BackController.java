package com.qf.back.v2.consumer.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.qf.back.v2.consumer.service.IBackService;
import com.qf.dto.PageBean;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import com.qf.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("back")
public class BackController {

    @Autowired
    private IBackService service;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(true);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }
    @RequestMapping("login")
    public String login(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password,ModelMap map){
        ResultBean resultBean = service.login(username,password);
        if (resultBean.getData() != null) {
            map.put("user",resultBean.getData());
            return "index";
        }
        return "loging";
    }

    @RequestMapping("orderList")
    public String orderList(PageBean pageBean,String account,ModelMap map){
        ResultBean resultBean = service.orderList(pageBean,account);
        map.put("pageInfo",resultBean.getData());
        map.put("account",account);
        map.put("url","/back/orderList");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("account",account);
        map.put("params",new Gson().toJson(paramMap));
        return "oList";
    }

    @RequestMapping("updateOrderInit")
    public String updateOrderInit(Integer id, ModelMap map){
        ResultBean resultBean = service.updateOrderInit(id);
        map.put("order",resultBean.getData());
        return "updateOrder";
    }

    @RequestMapping("updateOrderById")
    @ResponseBody
    public ResultBean updateOrderById(@RequestBody @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")TOrder order){
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
    public String productList(PageBean pageBean,String pname,Integer typeId,ModelMap map){
        ResultBean resultBean = service.productList(pageBean,pname,typeId);
        ResultBean tProductTypes = service.selectProductType();
        map.put("productType",tProductTypes.getData());
        map.put("typeId",typeId);
        map.put("pageInfo",resultBean.getData());
        map.put("pname",pname);
        map.put("url","/back/productList");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pname",pname);
        map.put("params",new Gson().toJson(paramMap));
        return "pList";
    }

    @RequestMapping("updateProductInit")
    public String updateProductInit(Integer id,ModelMap map){
        ResultBean resultBean = service.updateProductInit(id);
        ResultBean tProductTypes = service.selectProductType();
        map.put("productType",tProductTypes);
        map.put("product",resultBean.getData());
        return "updateProduct";
    }

    @RequestMapping("updateProductById")
    @ResponseBody
    public ResultBean updateProductById(@RequestBody @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")TProduct product){
        return service.updateProductById(product);
    }

    @RequestMapping("addProductInit")
    public String addProductInit(Integer id, ModelMap map, HttpServletRequest request){
        ResultBean resultBean = service.addProductInit(id);
        if (resultBean.getData() != null) {
            map.put("user",resultBean.getData());
            return "addProduct";
        }
        TUser user = (TUser)request.getAttribute("user");
        System.out.println("添加商品初始化错误！");
        return "forward:/back/addProductInit?id="+user.getId();
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
