package com.watchmoreonline.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "users";
	
	@Override
	public User checkByEmail(String email) {
		Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class, COLLECTION); 
	}

	public void update(User User) {
		mongoTemplate.save(User);
	}

	@Override
	public void insert(User User) {
		mongoTemplate.insert(User);
	}

	@Override
	public void delete(User User) {
		mongoTemplate.remove(User);
	}

	@Override
	public User findByPassword(String email, String password) {
		Query query = new Query(Criteria.where("email").is(email).and("password").is(password));
		 return mongoTemplate.findOne(query, User.class, COLLECTION); 
	}

}
