package com.qf.dto;

/*
 *SELECT
  a.pid AS id,
  a.pname AS t_product_name,
  a.sale_price AS t_product_sale_price,
  a.pimage AS t_product_pimage,
  b.pdesc AS t_product_pdesc
FROM
  t_product a
  LEFT JOIN t_product_desc b
    ON a.pid = b.`pid`
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TProductSearchDTO implements Serializable {

    private Long id;
    private String tProductName;
    private BigDecimal tProductSalePrice;
    private String tProductPimage;
    private String tProductPdesc;

}
