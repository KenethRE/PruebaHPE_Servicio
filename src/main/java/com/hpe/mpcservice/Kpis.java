package com.hpe.mpcservice;

import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.collect.Multimap;
@Entity
public class Kpis {
	private @Id @GeneratedValue Long id;
	private int processed_files;
	private int number_rows;
	private int number_calls;
	private int number_msg;
	private int origin_codes;
	private int destination_code;
	public Kpis(int processed_files, int number_rows, int number_calls, int number_msg,
			int origin_codes, int destination_code) {
		super();
		this.processed_files = processed_files;
		this.number_rows = number_rows;
		this.number_calls = number_calls;
		this.number_msg = number_msg;
		this.origin_codes = origin_codes;
		this.destination_code = destination_code;
	}


}
