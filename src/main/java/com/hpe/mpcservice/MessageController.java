package com.hpe.mpcservice;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.persistence.ElementCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

@RestController
class MessageController {

  private MessageRepo repository;
  private static final Logger log = LoggerFactory.getLogger(MessageController.class);
  private int incorrect_lines = 0;
  private int missing_fields = 0;
  private int empty_messages = 0;
  private int processed_files = 0;
  private int processed_rows = 0;
  private int number_calls = 0;
  private int number_msg = 0;
  @ElementCollection Map <String, ArrayList<String>> origin_destination = new HashMap<>();
  private int OK_Calls = 0;
  private int KO_Calls = 0;
  private int total_duration = 0;
  private ArrayList<String> origin_codes = new ArrayList<String>();
  private ArrayList<String> destination_codes = new ArrayList<String>();
  
  
  MessageController(MessageRepo repository) {
    this.repository = repository;
  }
  
  @GetMapping("/metrics")
  @ResponseBody
  String metrics(@RequestParam (defaultValue="20180131") String date) {
	  repository.deleteAll();
	  loadMessagesDates(repository, date);
	  double avg_duration = total_duration / number_calls;
	  Metrics metrics = new Metrics(missing_fields, empty_messages, incorrect_lines, origin_destination, OK_Calls, KO_Calls, avg_duration);
	  Gson gson = new Gson();
	  return gson.toJson(metrics);
  }
  
  
  @GetMapping("/kpis")
  @ResponseBody
  String kpis(@RequestParam (defaultValue="20180131") String date) {
	  Kpis kpis = new Kpis(processed_files, processed_rows, number_calls, number_msg, origin_codes.size(), destination_codes.size());
	  Gson gson = new Gson();
	  return gson.toJson(kpis);
  }
  
  public void loadMessagesDates(MessageRepo repository, String request_date) {
  	URL log_file;
		try {
			log_file = new URL("https://raw.githubusercontent.com/vas-test/test1/master/logs/MCP_" + request_date + ".json");
			Scanner sc;
			sc = new Scanner(log_file.openStream());
			while (sc.hasNextLine()) {
			try {
				processed_rows++;
				String next = sc.nextLine();
				Gson g = new Gson();
				JSONObject obj = new JSONObject(next);
				Iterator<String> keys = obj.keys();
				
				while(keys.hasNext()) {
					String key = keys.next();
					
					switch(key) {
					
					case "message_type":
						if (obj.getString(key).equals("")){
							missing_fields++;
						} else if (obj.getString("message_type").equals("CALL")) {
							number_calls++;
							} else if (obj.getString("message_type").equals("MSG")) {
							number_msg++;
							}
						break;
					
					case "timestamp":
						if (obj.getString(key).equals("")){
							missing_fields++;
						}
						break;
					case "origin":
						Double value = obj.getDouble(key);
						if (value == 0) {
							missing_fields++;
						} else {
							ArrayList<String> phone_numbers_origin = new ArrayList<String>();
							ArrayList<String> temp_origin = new ArrayList<String>();
							String country_code_origin = value.toString().substring(0, 2);
							temp_origin.add(country_code_origin);
							Set<String> temp_set1 = new HashSet<>(temp_origin);
							origin_codes.addAll(temp_set1);
							phone_numbers_origin.add(value.toString().substring(3, 9));
							origin_destination.put(country_code_origin, phone_numbers_origin);
						}
						break;
					case "destination":
						Double value2 = obj.getDouble(key);
						if (value2 == 0) {
							missing_fields++;
						} else {
							ArrayList<String> phone_numbers_destination = new ArrayList<String>();
							ArrayList<String> temp_destination = new ArrayList<String>();
							String country_code_destination = value2.toString().substring(0, 2);
							temp_destination.add(country_code_destination);
							Set<String> temp_set2 = new HashSet<>(temp_destination);
							destination_codes.addAll(temp_set2);
							phone_numbers_destination.add(value2.toString().substring(3, 9));
							origin_destination.put(country_code_destination, phone_numbers_destination);
						}
						break;
					case "duration":
						if (obj.getDouble(key) == 0) {
							missing_fields++;
							} else {
								total_duration += obj.getInt(key);
							}
						break;
						
					case "status_code":
						if (obj.getString(key).equals("")) {
							missing_fields++;
							} else {
								String status_code = obj.getString(key);
								if (status_code.equals("OK")){
									OK_Calls++;
								} else if (status_code.equals("KO")) {
									KO_Calls++;
								} else {
									missing_fields++;
								}
							}
						break;
						
					case "message_content":
						if (obj.getString(key).equals("")) {
							empty_messages++;
						}
						break;
					}
					
				}
			} catch (JsonParseException e) {
				incorrect_lines++;
				continue;
			} catch (JSONException s) {
				incorrect_lines++;
				continue;
			}
			
		}
			sc.close();
			processed_files++;
	} catch (IOException e1) {
	e1.printStackTrace();
}
}
  
}
