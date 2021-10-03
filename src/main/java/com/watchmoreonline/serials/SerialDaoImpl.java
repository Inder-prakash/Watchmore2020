package com.watchmoreonline.serials;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.watchmoreonline.moviebase.MovieBase;


@Repository("SerialDao")
public class SerialDaoImpl implements SerialDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "serials";

	public void insert(Serial m) {
		mongoTemplate.insert(m);
	}

	public void update(Serial m) {
		mongoTemplate.save(m);
	}

	public void delete(Serial m) {
		mongoTemplate.remove(m);
	}

	public Serial find(String id) {
		Serial m = mongoTemplate.findById(id, Serial.class, COLLECTION);
		return m;	
	}

	public List<Serial> findAll() {
		return (List <Serial> ) mongoTemplate.findAll(Serial.class);
	}

	@Override
	public List<Serial> publicTv() {
		Query query = new Query(Criteria.where("Status").is("Public"));
        return mongoTemplate.find(query,Serial.class);
	}
}
