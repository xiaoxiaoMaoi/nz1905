package com.qf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class TOrder implements Serializable {
    @Id
    private Integer id;

    private String account;

    private Integer payType;

    private Integer carry;

    private BigDecimal rebate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    private String expressCompanyName;

    private String lowStocks;

    private String confirmSendProductRemark;

    private String closedComment;

    private Integer score;

}