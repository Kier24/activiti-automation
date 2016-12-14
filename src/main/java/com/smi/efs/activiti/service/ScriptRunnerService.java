package com.smi.efs.activiti.service;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import expectj.ExpectJ;
import expectj.ExpectJException;
import expectj.Spawn;
import expectj.TimeoutException;

@Service
public class ScriptRunnerService {

	private static final Log LOGGER = LogFactory.getLog(ScriptRunnerService.class);

	public void runScript(String script) throws IOException, TimeoutException, ExpectJException {

		ExpectJ expectJ = new ExpectJ();

		Spawn spawn = expectJ.spawn(script);

		spawn.expectClose();

		LOGGER.info("Script finished processing.");
		LOGGER.info("Exit value: " + spawn.getExitValue());
		
		if (spawn.getExitValue() != 0){
			throw new RuntimeException("Script " + script + " failed to process.");
		}
	}
}
