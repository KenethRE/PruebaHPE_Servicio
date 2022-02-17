package com.hpe.mpcservce;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class MessageMSG {
	private @Id @GeneratedValue Long id;
    private String message_type;
    private long timestamp;
    private long origin;
    private long destination;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getOrigin() {
		return origin;
	}

	public void setOrigin(long origin) {
		this.origin = origin;
	}

	public long getDestination() {
		return destination;
	}

	public void setDestination(long destination) {
		this.destination = destination;
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
