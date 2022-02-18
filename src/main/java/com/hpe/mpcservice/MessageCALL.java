package com.hpe.mpcservice;

import javax.persistence.*;
@Entity
public class MessageCALL extends Message{
	
    private int duration;
    private String status_code;
    private String status_description;
    
    public MessageCALL() {}
    
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
