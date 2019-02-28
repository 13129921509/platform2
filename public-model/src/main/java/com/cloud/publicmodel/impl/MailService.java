package com.cloud.publicmodel.impl;

import org.springframework.stereotype.Component;


public interface MailService {
    void sendSimpleEmail(String to, String subject, String content);
}
