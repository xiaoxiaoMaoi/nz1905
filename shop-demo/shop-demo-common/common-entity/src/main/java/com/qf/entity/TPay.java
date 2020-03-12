package com.qf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TPay {
    @Id
    private Integer id;

    private String name;

    private String code;

    private String seller;

    private Integer order1;

    private String status;

    private String partner;

    private String key1;

    private String icon;

    private String picture;

}