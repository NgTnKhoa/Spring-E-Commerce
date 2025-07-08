package com.ngtnkhoa.springecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender javaMailSender;

  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setFrom("21130079@st.hcmuaf.edu.vn");
    message.setSubject(subject);
    message.setText(body);
    javaMailSender.send(message);
  }
}
