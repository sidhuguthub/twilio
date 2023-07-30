package com.twilio.sms.api.controller;

import com.twilio.sms.api.payload.WhatsappRequest;
import com.twilio.sms.api.service.TwilioWhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WhatsappController {



    @Autowired
    private TwilioWhatsappService twilioWhatsappService;

    //http://localhost:8080/api/send-whatsapp

    @PostMapping("/send-whatsapp")
    public ResponseEntity<String> sendWhatsapp(@RequestBody WhatsappRequest whatsappRequest) {
        String toPhoneNumber = whatsappRequest.getPhoneNumber();
        String message = whatsappRequest.getMessage();

        twilioWhatsappService.sendWhatsappMessage(toPhoneNumber, message);
        return ResponseEntity.status(HttpStatus.CREATED).body("WhatsApp message sent successfully");
    }
}

