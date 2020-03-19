package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class TProduct implements Serializable{

  @Id
  private Long pid;
  private String pname;
  private Double price;
  private Double salePrice;
  private Long typeId;
  private Long status;
  private String pimage;
  private Long flag;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  private Long createUser;
  private Long updateUser;

}
