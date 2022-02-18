package com.hpe.mpcservice;

import javax.persistence.*;
@Entity
public class Message {
	protected @Id @GeneratedValue Long id;
	protected String message_type;
	protected long timestamp;
    protected long origin;
    protected long destination;
    
    public Message() {}
    
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
}
