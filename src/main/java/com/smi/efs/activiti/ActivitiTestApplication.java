package com.smi.efs.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivitiTestApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		
		SpringApplication.run(ActivitiTestApplication.class, args);
	}
	
}
