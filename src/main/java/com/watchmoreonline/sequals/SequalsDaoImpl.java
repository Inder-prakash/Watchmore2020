package com.watchmoreonline.sequals;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.sequals.SequalsDao;

@Repository("SequalsDao")
public class SequalsDaoImpl implements SequalsDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "sequals";

	public void insert(Sequals m) {
		mongoTemplate.insert(m);
	}

	public void update(Sequals m) {
		mongoTemplate.save(m);
	}

	public void delete(Sequals m) {
		mongoTemplate.remove(m);
	}

	public Sequals find(String id) {
		Query query = new Query(Criteria.where("baseid").is(id));	
		Sequals resultSet = mongoTemplate.findOne(query, Sequals.class, COLLECTION);
		return resultSet;
	}

	public List<Sequals> findAll() {
		return (List <Sequals> ) mongoTemplate.findAll(Sequals.class);
	}

	@Override
	public Sequals findByBaseId(String id) {
		Query query = new Query(Criteria.where("baseid").is(id));
        return mongoTemplate.findOne(query,Sequals.class);
	}
	
}
