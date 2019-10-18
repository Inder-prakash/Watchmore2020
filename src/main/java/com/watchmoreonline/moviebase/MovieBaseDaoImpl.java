package com.watchmoreonline.moviebase;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
	
	
}
