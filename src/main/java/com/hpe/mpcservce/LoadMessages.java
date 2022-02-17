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
	  CommandLineRunner initDatabase(MessageRepoCALL repositoryCALL, MessageRepoMSG repositoryMSG) {
		
		
	    return args -> {
			File logs_folder = new File("./logs");
			File[] logs = logs_folder.listFiles();
			Scanner sc;
			int incorrect_lines = 0; 
			
			try {
				for (int i = 0; i < logs.length; i++) {
					if (logs[i].isFile()) {
						sc = new Scanner(logs[i]);
						ArrayList<String> file_contents;
						while (sc.hasNextLine()) {
							String next = sc.nextLine();
							JSONObject obj = new JSONObject(next);
							Gson g = new Gson();
							if (obj.getString("message_type").equals("CALL")) {
								MessageCALL mensaje = g.fromJson(next, MessageCALL.class);
								log.info("Preloading " + repositoryCALL.save(mensaje));
							} else if (obj.getString("message_type").equals("MSG")) {
								MessageMSG mensaje = g.fromJson(next, MessageMSG.class);
								log.info("Preloading " + repositoryMSG.save(mensaje));
							}
						}
						sc.close();
					}
				}
			}catch (JsonParseException e) {
				incorrect_lines++;
			} catch (JSONException s) {
				incorrect_lines++;
			}
	   
	    };
	  }
	
}
