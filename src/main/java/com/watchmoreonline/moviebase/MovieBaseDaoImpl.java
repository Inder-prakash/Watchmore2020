package com.watchmoreonline.moviebase;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("MovieBaseDao")
public class MovieBaseDaoImpl implements MovieBaseDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "moviesbase";

	public void insert(MovieBase m) {
		mongoTemplate.insert(m);
	}

	public void update(MovieBase m) {
		mongoTemplate.save(m);
	}

	public void delete(MovieBase m) {
		mongoTemplate.remove(m);
	}

	public MovieBase find(String id) {
		MovieBase m = mongoTemplate.findById(id, MovieBase.class, COLLECTION);
		return m;	 
	}

	public List<MovieBase> findAll() {
		return (List <MovieBase> ) mongoTemplate.findAll(MovieBase.class);
	}
	
	public List<MovieBase> movieByStatus(String status) {
		Query query = new Query(Criteria.where("Status").is(status));
        return mongoTemplate.find(query,MovieBase.class);
	}

	@Override
	public List<MovieBase> movieByCategories(String genere , String status) {
		Query query = new Query(Criteria.where("Genere").is(genere).and("Status").is(status));
        return mongoTemplate.find(query,MovieBase.class);
	}

	@Override
	public List<MovieBase> movieByLanguage(String lang, String status) {
		Query query = new Query(Criteria.where("Language").is(lang).and("Status").is(status));
        return mongoTemplate.find(query,MovieBase.class);
	}
	
	
}
