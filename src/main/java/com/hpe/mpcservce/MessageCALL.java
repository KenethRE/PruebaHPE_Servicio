package com.hpe.mpcservce;

import javax.persistence.*;
@Entity
public class MessageCALL {
	
	private @Id @GeneratedValue Long id;
    private String message_type;
    private long timestamp;
    private long origin;
    private long destination;
    private int duration;
    private String status_code;
    private String status_description;
    
	public MessageCALL(String message_type, long timestamp, long origin, long destination, int duration, String status_code,
			String status_description) {
		super();
		this.message_type = message_type;
		this.timestamp = timestamp;
		this.origin = origin;
		this.destination = destination;
		this.duration = duration;
		this.status_code = status_code;
		this.status_description = status_description;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getStatus_description() {
		return status_description;
	}

	public void setStatus_description(String status_description) {
		this.status_description = status_description;
	}

    
}
