package com.watchmoreonline.moviebase;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.watchmoreonline.collection.MovieCollection;
import com.watchmoreonline.services.Responses;

@Repository("MovieBaseDao")
public class MovieBaseDaoImpl implements MovieBaseDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	Responses responses;
	
	final String COLLECTION = "moviesbase";
	final int size = 30;
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
		System.out.println(id);
		MovieBase m = mongoTemplate.findById(id, MovieBase.class, COLLECTION);
		return m;	 
	}
	
	public List<MovieBase> findAll(MovieBase m) {
		Pageable pageable = PageRequest.of(m.getPage()-1, size,Sort.by("id").descending());	
		Query query = new Query().with(pageable);	
		List<MovieBase> li = mongoTemplate.findAll(MovieBase.class);
		return responses.setMsg2(mongoTemplate.find(query,MovieBase.class),li.size());
		
	}
	
	public List<MovieBase> movieByStatus(MovieBase m) {
		Pageable pageable = PageRequest.of(m.getPage()-1, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("Status").is(m.getStatus())).with(pageable);
		Query query2 = new Query(Criteria.where("Status").is(m.getStatus()));
		List<MovieBase> li = mongoTemplate.find(query2,MovieBase.class);
        return responses.setMsg2(mongoTemplate.find(query,MovieBase.class),li.size());
	}

	@Override
	public List<MovieBase> movieByCategories(MovieBase m) {
		
		Pageable pageable = PageRequest.of(m.getPage()-1, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("Genere").is(m.getGenere()).and("Status").is(m.getStatus())).with(pageable);
		Query query2 = new Query(Criteria.where("Genere").is(m.getGenere()).and("Status").is(m.getStatus()));
		List<MovieBase> li = mongoTemplate.find(query2,MovieBase.class);
		return responses.setMsg2(mongoTemplate.find(query,MovieBase.class),li.size());
	}

	@Override
	public List<MovieBase> movieByLanguage(MovieBase m) {
		Pageable pageable = PageRequest.of(m.getPage()-1, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("Language").is(m.getLanguage()).and("Status").is(m.getStatus())).with(pageable);
		Query query2 = new Query(Criteria.where("Language").is(m.getLanguage()).and("Status").is(m.getStatus()));
		List<MovieBase> li = mongoTemplate.find(query2,MovieBase.class);
		return responses.setMsg2(mongoTemplate.find(query,MovieBase.class),li.size());
	}

	@Override
	public List<MovieBase> search(Integer page ,String text) {
		Pageable pageable = PageRequest.of(page-1, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("name").regex(text, "i").and("Status").is("Public") ).with(pageable);
		Query query2 = new Query(Criteria.where("name").regex(text, "i").and("Status").is("Public") );
		List<MovieBase> li = mongoTemplate.find(query2,MovieBase.class);
		return responses.setMsg2(mongoTemplate.find(query,MovieBase.class),li.size());
	}

	@Override
	public List<MovieBase> search2(Integer page, String text) {
		Pageable pageable = PageRequest.of(page-1, size,Sort.by("id").descending());	
		Query query = new Query(Criteria.where("name").regex(text, "i")).with(pageable);
		Query query2 = new Query(Criteria.where("name").regex(text, "i"));
		List<MovieBase> li = mongoTemplate.find(query2,MovieBase.class);
		return responses.setMsg2(mongoTemplate.find(query,MovieBase.class),li.size());
	}
	
}
