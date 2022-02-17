package com.hpe.mpcservce;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.*;

import com.google.gson.*;

@Configuration
public class LoadMessages {
	private static final Logger log = LoggerFactory.getLogger(LoadMessages.class);
	
	@Bean
	  CommandLineRunner initDatabase(MessageRepo repository) {
		
	    return args -> {
			File logs_folder = new File("./logs");
			File[] logs = logs_folder.listFiles();
			Scanner sc;
			int incorrect_lines = 0; 
			
			
				for (int i = 0; i < logs.length; i++) {
					if (logs[i].isFile()) {
						sc = new Scanner(logs[i]);
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
						sc.close();
					}
				}
			}
	   
	    };
	  }
	
}
