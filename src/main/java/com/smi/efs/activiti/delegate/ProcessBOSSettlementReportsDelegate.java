package com.smi.efs.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessBOSSettlementReportsDelegate implements JavaDelegate {
	
	private static final Log LOGGER = LogFactory.getLog(ProcessBOSSettlementReportsDelegate.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Execution process [BOS Settlement Reports]...");
	}

}