package com.smi.efs.activiti.service;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	private String subject;
	private String body;
	private String sender;
	private String receivers;

	public void sendEmail(String subject, String body, String sender, String receivers) {
		this.subject = subject;
		this.body = body;
		this.sender = sender;
		this.receivers = receivers;

		validateFields();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(receivers.split(","));
		message.setSubject(subject);
		message.setText(body);

		javaMailSender.send(message);
	}

	public void sendEmailWithAttachment(String subject, String body, String sender, String receivers, Path attachment)
			throws MessagingException {
		this.subject = subject;
		this.body = body;
		this.sender = sender;
		this.receivers = receivers;
		validateFields();
		Validate.isTrue(Files.exists(attachment), "File to attach does not exist: " + attachment);

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom(sender);
		helper.setTo(receivers.split(","));
		helper.setSubject(subject);
		helper.setText(body);

		// @formatter:off
		helper.addAttachment(attachment.getFileName().toString(), new FileSystemResource(attachment.toFile()));
		// @formatter:on

		javaMailSender.send(mimeMessage);
	}

	private void validateFields() {
		Validate.notEmpty(subject, "Subject must not be null");
		Validate.notEmpty(body, "Email body must not be null");
		Validate.notEmpty(receivers, "Receivers must not be null");
		Validate.notEmpty(sender, "Sender must not be null");

//		if (!EmailValidator.getInstance().isValid(sender)) {
//			throw new RuntimeException("Invalid email: " + sender);
//		}
//
//		for (String receiver : receivers.split(",")) {
//			if (!EmailValidator.getInstance().isValid(receiver)) {
//				throw new RuntimeException("Invalid email: " + receiver);
//			}
//		}
	}
}
