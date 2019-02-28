package com.cloud.shopserver.service;


import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public interface AnscyService extends Serializable {
    public void send() throws InterruptedException;
}
