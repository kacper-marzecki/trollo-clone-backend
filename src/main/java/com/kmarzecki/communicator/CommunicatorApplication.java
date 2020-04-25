package com.kmarzecki.communicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CommunicatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicatorApplication.class, args);
	}

}
