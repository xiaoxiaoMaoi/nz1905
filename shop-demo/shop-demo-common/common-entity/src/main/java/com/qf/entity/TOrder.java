package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class TOrder {
    @Id
    private Integer id;

    private String account;

    private Integer payType;

    private Integer carry;

    private BigDecimal rebate;

    private Date createdate;

    private String status;

    private String refundStatus;

    private BigDecimal amount;

    private BigDecimal fee;

    private BigDecimal ptotal;

    private Integer quantity;

    private String paystatus;

    private String updateAmount;

    private String expressCode;

    private String expressName;

    private String otherRequirement;

    private String remark;

    private String expressNo;

    private String expressCompanyname;

    private String lowStocks;

    private String confirmSendProductremark;

    private String closedComment;

    private Integer score;

}