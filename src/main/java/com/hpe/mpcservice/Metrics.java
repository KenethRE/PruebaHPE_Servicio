package com.hpe.mpcservice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.common.collect.Multimap;
@Entity
public class Metrics {
	private @Id @GeneratedValue Long id;
	private int missing_fields;
	private int empty_messages;
	private int incorrect_lines;
	@ElementCollection Map<String, ArrayList<String>> origin_destination;
	private int OK_Calls;
	private int KO_Calls;
	private double call_duration_by_country;
	public Metrics(int missing_fields, int empty_messages, int incorrect_lines, Map<String, ArrayList<String>> calls, int oK_Calls,
			int kO_Calls, double call_duration_by_country) {
		super();
		this.missing_fields = missing_fields;
		this.empty_messages = empty_messages;
		this.incorrect_lines = incorrect_lines;
		this.origin_destination = calls;
		OK_Calls = oK_Calls;
		KO_Calls = kO_Calls;
		this.call_duration_by_country = call_duration_by_country;
	}

}
