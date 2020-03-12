package com.qf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 搜索时所用的数据传输模板
 *
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TProductDTO implements Serializable {

    private Long pid;
    private String pname;
    private double salePrice;
    private String pimage;
    private String pdesc;
}
