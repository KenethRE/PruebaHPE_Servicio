package com.hpe.mpcservice;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

@RestController
class MessageController {

  private MessageRepo repository;
  private static final Logger log = LoggerFactory.getLogger(MessageController.class);
  private int incorrect_lines = 0;

  MessageController(MessageRepo repository) {
    this.repository = repository;
  }
  
  @GetMapping("/messages")
  @ResponseBody
  List<Message> all(@RequestParam (defaultValue="20180131") String date) {
	  repository.deleteAll();
	  loadMessagesDates(repository, date);
    return repository.findAll();
  }

  @GetMapping("/metrics")
  String metrics() {
	  Metrics metrics = new Metrics(incorrect_lines, incorrect_lines, incorrect_lines, null, incorrect_lines, incorrect_lines, incorrect_lines);
	  Gson gson = new Gson();
	  return gson.toJson(metrics);
  }
  
  public void loadMessagesDates(MessageRepo repository, String request_date) {
  	URL log_file;
		try {
			
			log_file = new URL("https://raw.githubusercontent.com/vas-test/test1/master/logs/MCP_" + request_date + ".json");
			Scanner sc;
			sc = new Scanner(log_file.openStream());
			while (sc.hasNextLine()) {
			try {
				String next = sc.nextLine();
				JSONObject obj = new JSONObject(next);
				
				Gson g = new Gson();
				if (obj.getString("message_type").equals("CALL")) {
					Message mensaje = g.fromJson(next, MessageCALL.class);
					log.info("Preloading " + repository.save(mensaje));
				} else if (obj.getString("message_type").equals("MSG")) {
					Message mensaje = g.fromJson(next, MessageMSG.class);
					log.info("Preloading " + repository.save(mensaje));
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
			
	} catch (IOException e1) {
	e1.printStackTrace();
}
}
  
}
