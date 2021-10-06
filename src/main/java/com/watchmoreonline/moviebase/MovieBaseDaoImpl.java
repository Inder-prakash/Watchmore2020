package com.watchmoreonline.moviebase;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("MovieBaseDao")
public class MovieBaseDaoImpl implements MovieBaseDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	final String COLLECTION = "moviesbase";
	final int size = 10;
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

	public List<MovieBase> findAll(Integer page) {
//		Query query = new Query().with(Sort.by(Sort.Direction.DESC,"id"));s
//		return mongoTemplate.find(query,MovieBase.class);
		System.out.println("Sfsfsfs");
		Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());	
		Query query = new Query().with(pageable);
		return mongoTemplate.find(query,MovieBase.class);
	}
	
//	public List<MovieBase> FindByPage(Integer page) {
//		Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());	
//		Query query = new Query().with(pageable);
//		return mongoTemplate.find(query,MovieBase.class);
//	}
	
	public List<MovieBase> movieByStatus(String status,Integer page) {
		Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("Status").is(status)).with(Sort.by(Sort.Direction.DESC,"id")).with(pageable);
        return mongoTemplate.find(query,MovieBase.class);
	}

	@Override
	public List<MovieBase> movieByCategories(String genere , String status,Integer page) {
		Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("Genere").is(genere).and("Status").is(status)).with(Sort.by(Sort.Direction.DESC,"id")).with(pageable);
        return mongoTemplate.find(query,MovieBase.class);
	}

	@Override
	public List<MovieBase> movieByLanguage(String lang, String status,Integer page) {
		Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("Language").is(lang).and("Status").is(status)).with(Sort.by(Sort.Direction.DESC,"id")).with(pageable);
        return mongoTemplate.find(query,MovieBase.class);
	}
	
	
}
