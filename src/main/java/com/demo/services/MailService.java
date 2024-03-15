package com.demo.services;

public interface MailService {
    public boolean sendMail(String from, String to, String subject, String content);
}
