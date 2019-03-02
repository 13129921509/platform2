package com.cloud.staticresources.remoteapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("commodity-server")
public interface CommodityRemoteApi {
    @RequestMapping("/order/img/{commodityCode}")
    public String getOrderListImg(@PathVariable("commodityCode") String commodityCode);
}
