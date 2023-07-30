package com.twilio.sms.api.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioWhatsappService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String twilioWhatsappNumber;

    public TwilioWhatsappService() {
        Twilio.init(accountSid, authToken);
    }

    public void sendWhatsappMessage(String toPhoneNumber, String messageBody) {
        PhoneNumber to = new PhoneNumber("whatsapp:" + toPhoneNumber);
        PhoneNumber from = new PhoneNumber("whatsapp:" + twilioWhatsappNumber);

        MessageCreator creator = Message.creator(to, from, messageBody);
        creator.create();
    }
}

