package com.watchmoreonline.movies;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository("MovieDao")
public class MovieDaoImpl implements MovieDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "movies";

	public void insert(Movie m) {
		mongoTemplate.insert(m);
	}

	public void update(Movie m) {
		mongoTemplate.save(m);
	}

	public void delete(Movie m) {
		mongoTemplate.remove(m);
	}

	public Movie find(String id) {
		Movie m = mongoTemplate.findById(id, Movie.class, COLLECTION);
		return m;	 
	}

	public List<Movie> findAll() {
		return (List <Movie> ) mongoTemplate.findAll(Movie.class);
	}
	
	
}
