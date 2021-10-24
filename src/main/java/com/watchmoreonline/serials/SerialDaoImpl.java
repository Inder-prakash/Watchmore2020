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
import com.watchmoreonline.services.Responses;


@Repository("SerialDao")
public class SerialDaoImpl implements SerialDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	Responses responses;
	
	final int size = 30;
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
	public List<Serial> findAll(Serial m) {
		Pageable pageable = PageRequest.of(m.getPage()-1,size,Sort.by("id").descending());	
		Query query = new Query();
		List<Serial> li = mongoTemplate.findAll(Serial.class);
		query = new Query().with(pageable);
		return responses.setMsg2( mongoTemplate.find(query,Serial.class), li.size());
	}

	@Override
	public List<Serial> getTvByStatus(Serial m) {
		Pageable pageable = PageRequest.of(m.getPage()-1, size,Sort.by("id").descending());	
		Query query2 = new Query(Criteria.where("Status").is(m.getStatus())).with(Sort.by(Sort.Direction.DESC,"id"));		
		Query query = new Query(Criteria.where("Status").is(m.getStatus())).with(Sort.by(Sort.Direction.DESC,"id")).with(pageable);
		List<Serial> li = mongoTemplate.find(query2,Serial.class);
        return responses.setMsg2( mongoTemplate.find(query,Serial.class), li.size());
	}

	@Override
	public List<Serial> search(Integer page, String text) {
		Pageable pageable = PageRequest.of(page-1, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("name").regex(text, "i").and("Status").is("Public") ).with(pageable);
		Query query2 = new Query(Criteria.where("name").regex(text, "i").and("Status").is("Public") );
		List<Serial> li = mongoTemplate.find(query2,Serial.class);
		return responses.setMsg2(mongoTemplate.find(query,Serial.class),li.size());
	}

	@Override
	public List<Serial> search2(Integer page, String text) {
		Pageable pageable = PageRequest.of(page-1, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("name").regex(text, "i")).with(pageable);
		Query query2 = new Query(Criteria.where("name").regex(text, "i"));
		List<Serial> li = mongoTemplate.find(query2,Serial.class);
		return responses.setMsg2(mongoTemplate.find(query,Serial.class),li.size());
	}

	@Override
	public List<Serial> findAll() {
		return mongoTemplate.findAll(Serial.class);
	}

}
