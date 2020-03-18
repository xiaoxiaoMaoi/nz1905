package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class TOrderlog implements Serializable {

    @Id
    private Integer id;

    private String orderid;

    private String account;

    private Date createdate;

    private String content;

    private String accountType;
}