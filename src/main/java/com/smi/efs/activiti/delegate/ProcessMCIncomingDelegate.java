package com.smi.efs.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;

import com.smi.efs.activiti.ActivitiTestApplication;

public class ProcessMCIncomingDelegate implements JavaDelegate {
	
	private static final Log LOGGER = LogFactory.getLog(ProcessMCIncomingDelegate.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Execution process [MC Incoming / Process T067 & T112 Files]...");
		
		ImagePath.add(ActivitiTestApplication.class.getCanonicalName() + "/images");
		
		Screen screen = new Screen();
		screen.click("show_desktop.png");
	}

}
