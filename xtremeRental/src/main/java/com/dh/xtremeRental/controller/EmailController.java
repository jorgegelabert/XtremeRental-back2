package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.EmailDto;
import com.dh.xtremeRental.repository.IEmailService;
import com.dh.xtremeRental.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDto emailDto){

        System.out.println("Mensaje Recibido" + emailDto);

        emailService.sendEmail(emailDto.getToUser(),emailDto.getSubject(),emailDto.getMessage());

        return ResponseEntity.ok("Mail enviado");
    }
}
