package com.example.twilio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan
public class TwilioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwilioApplication.class, args);
	}

}
