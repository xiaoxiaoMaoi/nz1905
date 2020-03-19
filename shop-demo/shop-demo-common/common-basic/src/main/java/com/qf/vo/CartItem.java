package com.qf.vo;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class CartItem implements Serializable {

    private Long productId;
    private int count;
    private Date updateTime;

}
