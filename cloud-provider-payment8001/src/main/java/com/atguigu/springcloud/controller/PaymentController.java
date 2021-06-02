package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

// @RestController 和 @Controller
// https://www.cnblogs.com/shuaifing/p/8119664.html
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    // 加@RequestBody的原因在于RestTemplate的调用长传的是Json格式数据而非form表单数据
    // form表单可以不使用@RequestBody
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功, serverPort" + serverPort, result);
        }else {
            return new CommonResult(444, "插入数据库失败" , null);
        }
    }

    // https://blog.csdn.net/a15028596338/article/details/84976223
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******插入结果" + payment + "\t" +"（^_^)!!!");

        if (payment != null) {
            return new CommonResult(200, "查询成功, serverPort" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID" + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String getTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }
}
