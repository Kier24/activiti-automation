package com.smi.efs.activiti.delegate;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.smi.efs.activiti.service.EmailSenderService;

@Component
public class ReportsDelegate implements JavaDelegate {

	private static final Log LOGGER = LogFactory.getLog(ReportsDelegate.class);

	@Autowired
	EmailSenderService emailSenderService;

	@Value("${j01.email.receivers}")
	String receivers;

	@Value("${cms.downloads.folder}")
	String downloadsFolder;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Executing TASK J01 [Reports]...");
		
		//TASK J01
		//downloadFiles(); - files already in folder for now.
		
		List<File> attachments = (List<File>) FileUtils.listFiles(Paths.get(downloadsFolder).toFile(), new String[]{"T140","T113","csv"}, true);
		
		emailSenderService.sendEmailWithAttachment("Task J01", "MFE Reports attached.", "acquiring-cms-automation@paymaya.com", receivers,
				attachments);
	}
}
