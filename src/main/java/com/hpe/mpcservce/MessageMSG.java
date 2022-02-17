package com.hpe.mpcservce;

import javax.persistence.Entity;
@Entity
public class MessageMSG extends Message{
    private int message_content;
    private String message_status;
    
	public MessageMSG(Long id, String message_type, long timestamp, long origin, long destination, int message_content,
			String message_status) {
		super();
		this.id = id;
		this.message_type = message_type;
		this.timestamp = timestamp;
		this.origin = origin;
		this.destination = destination;
		this.message_content = message_content;
		this.message_status = message_status;
	}

	public int getMessage_content() {
		return message_content;
	}

	public void setMessage_content(int message_content) {
		this.message_content = message_content;
	}

	public String getMessage_status() {
		return message_status;
	}

	public void setMessage_status(String message_status) {
		this.message_status = message_status;
	}

}
