package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class TOrderlog {

    @Id
    private Integer id;

    private String orderid;

    private String account;

    private Date createdate;

    private String content;

    private String accountType;
}