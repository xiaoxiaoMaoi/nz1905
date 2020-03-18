package com.qf.order.v1.consumer.controller;

import com.qf.bean.CartInfo;
import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import com.qf.cart.v2.consumer.controller.CartController;
import com.qf.cart.v2.service.service.impl.CartServiceImpl;
import com.qf.dto.ResultBean;
import com.qf.dto.TProductCartDTO;
import com.qf.entity.TOrder;
import com.qf.entity.TOrderdetail;
import com.qf.entity.TProduct;
import com.qf.entity.TUser;
import com.qf.order.v1.consumer.service.IOrderService;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 下订单
 * @Author Ray.Cheng
 * @Date 2020/3/14
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private CartServiceImpl cartService;

    /*
    1.用户点击结算按钮，
    2.判断用户是否登录，若未登录，跳转至登录页面
    3.若登录，跳到pay.html
    4.接收到order,orderDetail数据
    5.点击提交订单，跳转至支付页面
    6.支付成功后，去数据库order表和orderDetail表中添加数据记录
    7.跳转至success页面
     */

    /**
     * 结算
     * @param order
     * @param request
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addOrder(@RequestBody Order order, HttpServletRequest request){
        Object user = request.getAttribute("user");

        return insertAndPay(order,user);
    }

    /**
     * 组装订单数据，并跳到支付页面让用户支付
     * @param order
     * @param user
     * @return
     */
    private String insertAndPay(Order order, Object user) {

        if (user==null){
            return "当前用户没有登录";
        }
        TUser tUser= (TUser) user;
        ResultBean resultBean = cartService.showCart(((TUser) user).getId().toString());
//        List<TProductCartDTO> products= (List<TProductCartDTO>) resultBean.getData();
        CartInfo cartInfo = (CartInfo) resultBean.getData();
        if (cartInfo == null || cartInfo.getProductList().size() == 0) {
            throw new NullPointerException("购物车中没有可支付的商品!");
        }

        //设置订单相关数据
        order.setAccount(user.toString());
        order.setQuantity(cartInfo.getProductList().size());
        /*生成订单号：年份+0-9的四位数组成*/
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for (int i = 1; i <=5; i++) {
            sb.append(random.nextInt(10));
        }
        order.setId(year+sb.toString());
        DecimalFormat df = new DecimalFormat("0.00");

        //创建订单明细集合
        List<Orderdetail> orderdetailList = new LinkedList<Orderdetail>();
        for (int i = 0; i < cartInfo.getProductList().size(); i++) {
            TProduct product = cartInfo.getProductList().get(i);
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setOrderID(Integer.parseInt(order.getId()));
            orderdetail.setProductID(product.getPid().intValue());
            orderdetail.setPrice(product.getSalePrice().toString());//商品现价
            orderdetail.setFee("0");//配送费
            orderdetail.setTotal0(String.valueOf(Double.valueOf(orderdetail.getPrice()) * orderdetail.getNumber()));//订单项小计
            orderdetailList.add(orderdetail);
        }
        if(orderdetailList.size()==1){
            order.setRemark(orderdetailList.get(0).getProductName());
        }else{
            order.setRemark("合并|"+orderdetailList.size()+"笔订单");
        }
        cartInfo.totalCacl();
        order.setExpressCode(null);//配送方式编码
        order.setExpressName(null);//配送方式名称
        order.setFee(String.valueOf(null));//订单配送费
        order.setPtotal(cartInfo.getAmount());//订单商品总金额
        order.setAmount(String.valueOf(Double.valueOf(cartInfo.getAmount())+Double.valueOf(order.getFee())));//订单总金额 = 内存订单总金额 + 总配送费
        /**
         * 对金额进行格式化，防止出现double型数字计算造成的益出。
         */
        order.setAmount(df.format(Double.valueOf(order.getAmount())));//订单总金额
        order.setPtotal(df.format(Double.valueOf(order.getPtotal())));//订单商品总金额
        order.setFee(df.format(Double.valueOf(order.getFee())));//订单总配送费

        int result=orderService.createOrder(order, orderdetailList);
        if (result>0){
            return "下单成功";
        }

        return "下单失败";

    }


}
