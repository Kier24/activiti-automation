package com.smi.efs.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smi.efs.activiti.service.ScriptRunnerService;

@Component
public class CreateMCOutgoingDelegate implements JavaDelegate {
	
	private static final Log LOGGER = LogFactory.getLog(CreateMCOutgoingDelegate.class);

	@Autowired
	ScriptRunnerService scriptRunnerService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Execution process [MC Outgoing]...");
		
		scriptRunnerService.runScript("src/main/resources/expect_scripts/mc_outgoing.tcl");
	}

}
