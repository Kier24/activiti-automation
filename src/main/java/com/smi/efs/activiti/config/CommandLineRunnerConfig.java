package com.smi.efs.activiti.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.engine.RuntimeService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunnerConfig {
	
	@Value("${config.directory.projects}")
	private String projectsDirectoryName;
	
	private Path projectsDirectory;

	@Bean
	public CommandLineRunner init(RuntimeService runtimeService) {
		projectsDirectory = Paths.get(projectsDirectoryName);
		Validate.isTrue(Files.isDirectory(projectsDirectory), projectsDirectoryName + " is not a valid directory");
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		try {
			variables.put("mdfDirectory", getDirectory("efs-jcb-batch"));
			variables.put("stopfileDirectory", getDirectory("efs-jcb-stopfile"));
		} catch (IOException e) {
			throw new RuntimeException("Error getting directory names", e);
		}
		
		return args -> runtimeService.startProcessInstanceByKey("processMdfAndSdf", variables);
	}
	
	private String getDirectory(String projectName) throws IOException {
		List<Path> paths = Files.walk(projectsDirectory)
			.filter(t -> t.getFileName().toString().contains(projectName) && Files.isDirectory(t))
			.collect(Collectors.toCollection(ArrayList::new));
		
		Validate.isTrue(paths.size() == 1, "Multiple project directories found for " + projectName);
		
		return paths.get(0).toAbsolutePath().toString();
	}
	
}
