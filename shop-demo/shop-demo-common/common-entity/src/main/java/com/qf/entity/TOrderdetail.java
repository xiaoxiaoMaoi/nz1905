package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class TOrderdetail {

    @Id
    private Integer id;

    private Integer orderId;

    private Integer productId;

    private BigDecimal price;

    private Integer number;

    private String productName;

    private BigDecimal fee;

    private BigDecimal total0;

    private String isComment;

    private String lowStocks;

    private Integer score;

    private String specInfo;

    private String giftId;

}