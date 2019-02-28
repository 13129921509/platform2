package com.cloud.shopserver.service;

public interface MailService {
    void sendSimpleEmail(String to, String subject, String content);
}
