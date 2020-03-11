package com.qf.execption;

import lombok.Getter;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
@Getter
public class NZExecption extends RuntimeException{

    private Integer code;

    public NZExecption(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public NZExecption() {
    }
}
