package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class TUser implements Serializable{

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
