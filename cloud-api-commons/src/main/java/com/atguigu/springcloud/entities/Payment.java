package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor // 有参构造
@NoArgsConstructor  // 无参构造
// 实现序列化
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
