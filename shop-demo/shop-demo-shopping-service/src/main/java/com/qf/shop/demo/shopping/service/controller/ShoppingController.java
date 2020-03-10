package com.qf.shop.demo.shopping.service.controller;

import com.qf.entity.TProduct;
import com.qf.entity.TProductType;
import com.qf.shop.demo.shopping.service.service.impl.ProductServiceImpl;
import com.qf.shop.demo.shopping.service.service.impl.ProductTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
public class ShoppingController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductTypeServiceImpl productTypeService;

    @RequestMapping("hi")
    @ResponseBody
    public String sayHi(String message) {
        return String.format("当前是来自于%s的消息：%s", serverPort, message);
    }

    @RequestMapping("init")
    public String init(ModelMap map){
        List<TProduct> productList = productService.getList();
        List<TProductType> productTypeList = productTypeService.getList();
        map.put("productList",productList);
        map.put("productTypeList",productTypeList);
        System.out.println(productList);
        System.out.println(productTypeList);
        return "index";
    }


}
