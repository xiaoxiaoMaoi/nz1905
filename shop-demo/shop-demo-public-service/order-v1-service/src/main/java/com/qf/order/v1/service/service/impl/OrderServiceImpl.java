package com.qf.order.v1.service.service.impl;

import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import com.qf.entity.TOrder;
import com.qf.entity.TOrderdetail;
import com.qf.mapper.TOrderMapper;
import com.qf.mapper.TOrderdetailMapper;
import com.qf.order.v1.service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.AssociationOverride;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/19
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private TOrderMapper orderMapper;

    @Autowired
    private TOrderdetailMapper orderdetailMapper;

    @Override
    public int createOrder(Order order, List<Orderdetail> orderdetailList) {
        TOrder tOrder=new TOrder();
        tOrder.setId(Integer.parseInt(order.getId()));
        tOrder.setAccount(order.getAccount());
        tOrder.setCreatedate(new Date());
        tOrder.setQuantity(order.getQuantity());
        tOrder.setAmount(new BigDecimal(order.getAmount()));
        tOrder.setPtotal(new BigDecimal(order.getPtotal()));
        int result=orderMapper.insert(tOrder);
        if (result>0){
            TOrderdetail tOrderdetail=new TOrderdetail();
            for (Orderdetail orderdetail : orderdetailList) {
                tOrderdetail.setOrderId(orderdetail.getOrderID());
                tOrderdetail.setPrice(new BigDecimal(orderdetail.getPrice()));
                tOrderdetail.setProductId(orderdetail.getProductID());
                tOrderdetail.setFee(new BigDecimal(orderdetail.getFee()));
                tOrderdetail.setTotal0(new BigDecimal(orderdetail.getTotal0()));
            }
            int result1=orderdetailMapper.insert(tOrderdetail);
            return result1;
        }else {
            return result;
        }


    }
}
