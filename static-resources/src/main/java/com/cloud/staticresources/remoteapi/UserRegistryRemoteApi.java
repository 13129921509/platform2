package com.cloud.staticresources.remoteapi;

import com.cloud.publicmodel.entity.RegistryUserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@FeignClient("user-server")
@Component
public interface UserRegistryRemoteApi {
    @RequestMapping(value = "/registrycode",method = RequestMethod.POST)
    boolean registrycode(@RequestBody HashMap map);

    @RequestMapping(value = "/comparisonregistrycode",method = RequestMethod.POST)
    boolean comparisonregistrycode(@RequestBody Map map);

    @RequestMapping(value = "/user/registry",method = RequestMethod.PUT)
    void registry(@RequestBody RegistryUserEntity map);
}
