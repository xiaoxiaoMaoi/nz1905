package com.qf.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class TProductType {
  @Id
  private Long cid;

  private String cname;
  private Long pid;
  private Long flag;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  private Long createUser;
  private Long updateUser;

}
