package com.twilio.service;

import com.twilio.Twilio;
import com.twilio.payload.SmsMessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    public void sendSms(SmsMessageDTO smsMessageDTO) {
        try {
            // Initialize Twilio client
            Twilio.init(accountSid, authToken);

            // Send SMS using Twilio
            Message.creator(
                    new PhoneNumber(smsMessageDTO.getRecipientPhoneNumber()),
                    new PhoneNumber(phoneNumber),
                    smsMessageDTO.getMessage()
            ).create();
        } catch (ApiException ex) {
            // Handle Twilio API exception
            throw new RuntimeException("Failed to send SMS: " + ex.getMessage());
        }
    }
}


