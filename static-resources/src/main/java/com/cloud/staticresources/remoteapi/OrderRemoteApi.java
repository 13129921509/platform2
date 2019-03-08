package com.cloud.staticresources.remoteapi;

import com.cloud.publicmodel.entity.OrderDetailsEntity;
import com.cloud.publicmodel.entity.OrderEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient("order-server")
public interface OrderRemoteApi {
    /**
     * 通过order服务回传订单
     */
    @RequestMapping("/order/userEmail/{userEmail}")
    List<OrderEntity> getOrdersList(@PathVariable("userEmail") String userEmail);

    @RequestMapping("/orderCode/{orderCode}")
    List<OrderDetailsEntity> getOrderDetailsEntities(@PathVariable("orderCode") String orderCode);

    }
