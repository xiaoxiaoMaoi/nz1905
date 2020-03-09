package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TProduct {

  @Id
  private Long pid;
  private String pname;
  private double price;
  private double salePrice;
  private Long typeId;
  private Long status;
  private String pimage;
  private Long flag;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  private Long createUser;
  private Long updateUser;

}
