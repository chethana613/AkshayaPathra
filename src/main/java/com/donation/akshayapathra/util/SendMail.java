package com.donation.akshayapathra.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMail {
	
	@Autowired
	MailSender mailSender;

	public void SendMailToDonor(String toAddress, String subject, String msgBody) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setFrom("muthupalaniappan552@gmail.com");
		message.setTo(toAddress);
		message.setSubject(subject);
		message.setText(msgBody);
		mailSender.send(message);
	}
}
