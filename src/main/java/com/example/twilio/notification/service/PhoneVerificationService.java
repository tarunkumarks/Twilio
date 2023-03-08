package com.example.twilio.notification.service;

import com.example.twilio.notification.enums.Channel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PhoneVerificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneVerificationService.class);

    @Value("${twilio.account.sid:ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXX}")
    public String accountSId;
    @Value("${twilio.auth.token:9aXXXXXXXXXXXXXXXXXXXXXXXXXXXXX}")
    public String authToken;

    @Value("${twilio.auth.service.sid:VXXXXXXXXXXXXXXXXXXXX}")
    public String serviceSid;

    public void start( String identifier, Channel channel ) {

        if(!identifier.contains("@")) {
            identifier = "+91" + identifier;
        }
        Twilio.init(accountSId,authToken);
        Verification verification = Verification.creator(
                        serviceSid, // this is your verification sid
                        identifier, //this is your Twilio verified recipient phone number
                        channel.name()) // this is your channel type
                .create();

        System.out.println(verification.getStatus());

        LOGGER.info("OTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());
    }

    public void verify(String identifier, String token) {
        Twilio.init(accountSId,authToken);
        if(!identifier.contains("@")) {
            identifier = "+91" + identifier;
        }
        try {

            VerificationCheck verificationCheck = VerificationCheck.creator(
                            serviceSid)
                    .setTo(identifier)
                    .setCode(token)
                    .create();

            System.out.println(verificationCheck.getStatus());

        } catch (Exception e) {
            System.out.println("Error"+ e);
        }
        System.out.println("OTP Verified Successfully");

    }

    public void sendSMS() {

        Twilio.init(accountSId, authToken);

        Message.creator(new PhoneNumber("+919790794687"),
                new PhoneNumber("+12765826739"), "Hello from Twilio 📞").create();
    }

}
