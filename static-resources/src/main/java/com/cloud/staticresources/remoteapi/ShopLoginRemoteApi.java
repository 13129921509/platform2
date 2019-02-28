package com.cloud.staticresources.remoteapi;

import com.cloud.publicmodel.entity.LoginUserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Component
@FeignClient("shop-server")
public interface ShopLoginRemoteApi {

    @RequestMapping(value = "/loginCode",method = RequestMethod.POST)
    boolean loginCode(@RequestBody HashMap map);

    @RequestMapping(value = "/shop/login",method = RequestMethod.POST)
    String login(@RequestBody LoginUserEntity user);
}
