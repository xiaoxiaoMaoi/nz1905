package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TOrdership {

    @Id
    private Integer id;

    private String orderid;

    private String shipname;

    private String shipaddress;

    private String provinceCode;

    private String province;

    private String cityCode;

    private String city;

    private String areaCode;

    private String area;

    private String phone;

    private String tel;

    private String zip;

    private String sex;

    private String remark;

}