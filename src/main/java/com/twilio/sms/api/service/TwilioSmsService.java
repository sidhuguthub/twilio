package com.twilio.sms.api.service;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        Twilio.init(accountSid, authToken);

        try {
            Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();
        } catch (ApiException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}

