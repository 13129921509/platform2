package com.cloud.staticresources.remoteapi;

import com.cloud.publicmodel.entity.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("shop-server")
public interface ShopRemoteApi {
    @RequestMapping(value = "/shop/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    Result getShopById(@PathVariable("id") String id);
}
