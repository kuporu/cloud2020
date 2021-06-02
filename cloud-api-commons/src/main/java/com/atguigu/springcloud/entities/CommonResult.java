package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code; // 状态码
    private String message; // 信息
    private T data; // 实体类

    // 两个参数的构造方法
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
