package com.watchmoreonline.serials;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.watchmoreonline.moviebase.MovieBase;


@Repository("SerialDao")
public class SerialDaoImpl implements SerialDao {

	@Autowired
	MongoTemplate mongoTemplate;
	final int size = 10;
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

	@Override
	public List<Serial> findAll(Integer page) {
		Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());	
		Query query = new Query().with(pageable);
		return mongoTemplate.find(query,Serial.class);
	}

	@Override
	public List<Serial> getTvByStatus(String status, Integer page) {
		Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("Status").is(status)).with(Sort.by(Sort.Direction.DESC,"id")).with(pageable);
        return mongoTemplate.find(query,Serial.class);
	}
}
