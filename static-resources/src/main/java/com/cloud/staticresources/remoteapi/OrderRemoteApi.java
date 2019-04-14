package com.cloud.staticresources.remoteapi;

import com.cloud.publicmodel.entity.OrderDetailsEntity;
import com.cloud.publicmodel.entity.OrderEntity;
import com.cloud.publicmodel.entity.OrderModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Component
@FeignClient("order-server")
public interface OrderRemoteApi {
    /**
     * 通过order服务回传订单
     */
    @RequestMapping("/order/userEmail/{userEmail}")
    List<OrderEntity> getOrdersList(@PathVariable("userEmail") String userEmail);

    @RequestMapping("/order/orderCode/{orderCode}")
    List<OrderDetailsEntity> getOrderDetailsEntities(@PathVariable("orderCode") String orderCode);

    /**
     * 新增订单
     * @return
     */
    @RequestMapping(value = "/order/add",method = RequestMethod.POST)
    String addOrderHeader(@RequestBody OrderModel orderModel);

    /**
     * 获得订单列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    List<OrderEntity> getOrderList(@RequestBody Map<String,String> map);
}
