package com.cloud.userserver.remoteApi;

import com.cloud.publicmodel.entity.OrderEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("order-server")
@Component
public interface OrderRemoteApi {
    /**
     * 通过order服务回传订单
     */
    @RequestMapping("/order/userEmail/{userEmail}")
    List<OrderEntity> getOrdersList(@PathVariable String userEmail);
}
