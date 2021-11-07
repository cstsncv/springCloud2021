package com.csts.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;

        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current +1;
            // CSA 通常CAS用于同步得方式是从通过Unsafe获得内存地址地址V读取atomicObject的原始值,
            // 然后和预期原值作比较计算,如果从地址V读出来的原始值和预期原始值一样,则把新值赋值到位置V上,
            // 也就是栈中的地址V对应的堆中的值就被改成新值
        } while (!this.atomicInteger.compareAndSet(current, next));

        System.out.println("***********current : "+ current+ "\tnext: "+ next);

        return next;
    }


    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
