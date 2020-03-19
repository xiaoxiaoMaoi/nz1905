package com.qf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private java.util.Date createTime;
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private java.util.Date updateTime;
  private Long createUser;
  private Long updateUser;

}
