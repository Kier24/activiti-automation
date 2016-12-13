package com.smi.efs.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ActivitiTestApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(ActivitiTestApplication.class, args);
	}
	

	@Bean
	CommandLineRunner init( final RepositoryService repositoryService,
	                              final RuntimeService runtimeService,
	                              final TaskService taskService) {

	    return new CommandLineRunner() {
	        public void run(String... strings) throws Exception {
	            Map<String, Object> variables = new HashMap<>();
	           	runtimeService.startProcessInstanceByKey("dailyBatch", variables);
	        }
	    };
	}
}
