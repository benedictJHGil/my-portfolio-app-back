package com.portfolio.myportfolioappback.service;

import org.springframework.stereotype.Service;

import com.portfolio.myportfolioappback.dto.ContactRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final JavaMailSender mailSender;

    @Value("${contact.to.email}")
    private String toEmail;

    public void sendContactMail(ContactRequestDto request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);

        message.setSubject("[포트폴리오 문의] " + request.title());

        StringBuilder sb = new StringBuilder();
        sb.append("문의 종류: ").append(request.type()).append("\n")
          .append("답변받을 곳: ").append(request.contact()).append("\n\n")
          .append("내용:\n")
          .append(request.message());

        message.setText(sb.toString());

        mailSender.send(message);
    }
}
