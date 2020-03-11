package com.qf.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据返回给前段页面的模板
 *
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {

    //状态码
    private Integer code;
    //返回的提示信息
    private String msg;
    //携带的数据
    private Object data;
}
