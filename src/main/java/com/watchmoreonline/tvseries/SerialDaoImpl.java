package com.watchmoreonline.tvseries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository("SerialDao")
public class SerialDaoImpl implements SerialDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "tvseries";

	public void insert(Serial s) {
		mongoTemplate.insert(s);
	}

	public void update(Serial s) {
		mongoTemplate.save(s);
	}

	public void delete(Serial s) {
		mongoTemplate.remove(s);
	}

	public Serial find(String id) {
		Serial s = mongoTemplate.findById(id, Serial.class, COLLECTION);
		return s;	
	}

	public List<Serial> findAll() {
		return (List <Serial> ) mongoTemplate.findAll(Serial.class);
	}
	
}
