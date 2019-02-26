package com.cloud.userserver.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface MailService {
    void sendSimpleEmail(String to,String subject,String content);
}
