package com.twilio.controller;

import com.twilio.exception.ApiException;
import com.twilio.payload.SmsMessageDTO;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.service.TwilioService;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {


    //http://localhost:8080/sms/send

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsMessageDTO smsMessageDTO) {

        try {
            twilioService.sendSms(smsMessageDTO);
            return ResponseEntity.ok("SMS sent successfully!");
        } catch (ApiException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }
}

