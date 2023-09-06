package com.example.gmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.gmail.service") 
public class GmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmailApplication.class, args);
	}

}
 