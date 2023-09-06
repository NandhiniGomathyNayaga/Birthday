package com.example.gmail.entity;

import jakarta.mail.internet.InternetAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {
	 //Class data members   
	private String recipient; 
	private String msgBody; 
	private String subject;
	private String attachment;
	
	 public String getRecipient() {
	        return recipient;
	    }

	    public String getMsgBody() {
	        return msgBody;
	    }

	    public String getSubject() {
	        return subject;
	    }

	    public String getAttachment() {
	        return attachment;
	    }

}
