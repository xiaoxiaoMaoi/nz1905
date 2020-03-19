package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class TOrderpay implements Serializable {

    @Id
    private Integer id;

    private String orderid;

    private String paystatus;

    private BigDecimal payamount;

    private Date createtime;

    private String paymethod;

    private Date confirmdate;

    private String confirmuser;

    private String remark;

    private String tradeNo;

}