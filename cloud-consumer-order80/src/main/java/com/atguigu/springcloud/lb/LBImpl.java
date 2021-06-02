package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LBImpl implements LoadBalancer{
    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstances) {
        int idx = getIdx() % serviceInstances.size();
        return serviceInstances.get(idx);
    }

    public final int getIdx() {
         int currt, next;
         do{
             currt = atomicInteger.get();
             next = (currt >= Integer.MAX_VALUE) ? 0: currt + 1;
         }while (!atomicInteger.compareAndSet(currt, next));
        System.out.println("*******当前次数" + next);
        return next;
    }
}
