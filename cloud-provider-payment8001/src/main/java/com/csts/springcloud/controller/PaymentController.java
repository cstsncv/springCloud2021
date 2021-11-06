package com.csts.springcloud.controller;

import com.csts.springcloud.entities.CommonResult;
import com.csts.springcloud.entities.Payment;
import com.csts.springcloud.service.PaymentService;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {
    //    @Autowired
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********insert into db: " + result);

        return new CommonResult(200, "success insert into db", result);
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***********get payment sucess, serverPort: " +serverPort + ", payment: " + payment.toString());
        if (payment != null){
            return new CommonResult(200, "get payment sucess, serverPort: " +serverPort, payment);
        } else {
            return new CommonResult(444, "no match id: " + id);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            log.info("get service name: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId()+ "\t"+ instance.getHost()+ "\t"+ instance.getPort()+ "\t"+ instance.getUri());
        }
        return this.discoveryClient;
    }
}
