package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TProductDesc {
  @Id
  private Long id;
  private Long pid;
  private String pdesc;
  private Long flag;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  private Long createUser;
  private Long updateUser;

}
