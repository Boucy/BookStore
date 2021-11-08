package com.boucy.service;

import javax.servlet.http.HttpSession;

public interface MailService {
    boolean sendMimeMail(String email, HttpSession session);
    String randomCode();
    boolean verify(String vsCode, String email);
}

