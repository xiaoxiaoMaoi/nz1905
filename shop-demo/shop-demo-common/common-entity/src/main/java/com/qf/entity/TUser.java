package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TUser {

  @Id
  private Long id;
  private String uname;
  private String password;
  private String phone;
  private String email;
  private Long flag;
  private java.util.Date createTime;
  private Long createUser;
  private java.util.Date updateTime;
  private Long updateUser;

}
