package com.watchmoreonline.collection;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.watchmoreonline.moviebase.MovieBase;

@Repository("MovieCollectionDao")
public class MovieCollectionDaoImpl implements MovieCollectionDao {
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "moviecollection";

	public void insert(MovieCollection m) {
		mongoTemplate.insert(m);
	}

	public void update(MovieCollection m) {
		mongoTemplate.save(m);
	}

	public void delete(MovieCollection m) {
		mongoTemplate.remove(m);
	}

	public MovieCollection find(String id) {
		MovieCollection m = mongoTemplate.findById(id, MovieCollection.class, COLLECTION);
		return m;	
	}

	public List<MovieCollection> findAll() {
		Query query = new Query().with(Sort.by(Sort.Direction.ASC,"name"));
		return mongoTemplate.find(query,MovieCollection.class);
	}

}
