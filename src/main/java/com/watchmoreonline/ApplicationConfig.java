package com.watchmoreonline;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Configuration
@ComponentScan(basePackages = {"com.watchmoreonline"})
@EnableWebMvc

public class ApplicationConfig {
	

//	@Bean
//	public MongoClient getMongoClient() {
//		MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017/?ServiceName=mongodb");
//		System.out.println("Connecting with Mongo Client ..............................");
//		return mongoClient;
//	}
//	
//	@Bean
//	public MongoDatabase getNameDatabase() {
//		
//			MongoDatabase database = getMongoClient().getDatabase("itv");
//			System.out.println("Connection Establish ..............................");
//			return database;
//	}
	
	@Bean
	public MongoClient getMongoClient() {
		MongoClient mongoClient = MongoClients.create("mongodb+srv://watchmore:watchmore123@watchmoreonline.aozwy.mongodb.net/watchmoreonline?retryWrites=true&w=majority");
		System.out.println("Connecting with Mongo Client ..............................");
		return mongoClient;
	}
	
	@Bean
	public MongoDatabase getNameDatabase() {
		
		MongoDatabase database = getMongoClient().getDatabase("watchmoreonline");
		System.out.println("Connection Establish ..............................");
		return database;
	}
	
	


 
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mgo = new MongoTemplate(getMongoClient(), getNameDatabase().getName());		
		return mgo;
	}	
}
