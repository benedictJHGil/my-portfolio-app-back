package com.portfolio.myportfolioappback.controller;

import java.util.Map;

import com.portfolio.myportfolioappback.dto.ContactRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portfolio.myportfolioappback.service.ContactService;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<?> sendContact(@RequestBody ContactRequestDto request) {
        contactService.sendContactMail(request);
        return ResponseEntity.ok(Map.of(
            "message", "문의가 성공적으로 전송되었습니다."
        ));
    }    
}
