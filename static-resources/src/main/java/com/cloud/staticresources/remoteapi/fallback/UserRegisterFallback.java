package com.cloud.staticresources.remoteapi.fallback;

import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.staticresources.remoteapi.UserRegistryRemoteApi;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterFallback implements UserRegistryRemoteApi {
    /**
     * 服务降级的操作：
     * 1.mongo记录日志
     * 2.发送邮件给用户，说明原因
     * @param map
     */
    public boolean registrycode(HashMap map) {
        return false;
    }

    public boolean comparisonregistrycode(Map map) {
        return false;
    }

    public void registry(RegistryUserEntity map) {

    }
}
