package com.example.gmail.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.gmail.entity.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	

    private final String recipient;
    private final String msgBody;
    private final String subject;
    private final String attachment;

    @Autowired
    public EmailServiceImpl(
            @Value("${email.recipient}") String recipient,
            @Value("${email.msgBody}") String msgBody,
            @Value("${email.subject}") String subject,
            @Value("${email.attachment}") String attachment) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
    }
   

	 @Autowired
	 private JavaMailSender javaMailSender;
		
		@Value("${spring.mail.username}") 
		private String sender;

	@Override
	public String sendSimpleMail(EmailDetails details) {
		
		try {
		// TODO Auto-generated method stub
			SimpleMailMessage mailmessage = new SimpleMailMessage();
			
			//setting up the details
			mailmessage.setFrom(sender);
			mailmessage.setTo(details.getRecipient());
			mailmessage.setText(details.getMsgBody());
			mailmessage.setSubject(details.getSubject());
			
			//sending the mail
			javaMailSender.send(mailmessage); 
			return "Mail Send Successfully";
	}
		//catch block to handle the exceptions
		catch(Exception e) {
			return "Error While Sending the Mail!!!";
		}
	}
	@Override
	public String sendMailWithAttachment(EmailDetails details) {
		
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());
		
			//adding the attachment
			FileSystemResource file=new FileSystemResource(new File(details.getAttachment()));
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			
			//sending the mail
			javaMailSender.send(mimeMessage);
			return "Mail Successfully sent";
					
		}
		catch(MessagingException e) {
		return "Error While Sending Mail";	
		}
	}
	
	
}
