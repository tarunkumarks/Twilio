package com.example.twilio.notification.controller;

import com.example.twilio.notification.enums.Channel;
import com.example.twilio.notification.service.PhoneVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SmsController {

    private PhoneVerificationService phoneVerificationService;

    public SmsController(PhoneVerificationService phoneVerificationService) {
        this.phoneVerificationService = phoneVerificationService;
    }

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {

        phoneVerificationService.sendSMS();
        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/sendOTP")
    public ResponseEntity<String> sendSMSForVerification(@RequestParam("phone") String phone) {

        System.out.println("Phone: "+phone);
        phoneVerificationService.start(phone, Channel.sms);
        return new ResponseEntity<String>("OTP sent successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/sendEmailOTP")
    public ResponseEntity<String> sendEmailForVerification(@RequestParam("email") String email) {

        System.out.println("email: "+email);
        phoneVerificationService.start(email, Channel.email);
        return new ResponseEntity<String>("OTP sent successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/verifyOTP")
    public ResponseEntity<String> verifySMSForOTP(@RequestParam("phone") String phone, @RequestBody String token) {

        System.out.println("Phone: "+phone);
        System.out.println("token: "+token);
        phoneVerificationService.verify(phone,token);
        return new ResponseEntity<String>("Message Verified successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/verifyEmailOTP")
    public ResponseEntity<String> verifyEmail(@RequestParam("email") String email, @RequestBody String token) {

        System.out.println("Phone: "+email);
        System.out.println("token: "+token);
        phoneVerificationService.verify(email,token);
        return new ResponseEntity<String>("Message Verified successfully", HttpStatus.OK);
    }
}
