package com.cloud.staticresources.remoteapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Component
@FeignClient("commodity-server")
public interface CommodityRemoteApi {
    /**
     *
     * @param commodityCode
     * @return 返回一个关于商品id的所有图片地址
     */
    @RequestMapping("/order/img/{commodityCode}")
    List<String> getOrderListImg(@PathVariable("commodityCode") String commodityCode);
}
