package com.watchmoreonline.services;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchmoreonline.users.User;
import com.watchmoreonline.users.UserDao;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;

@Service
public class UserService {
	
	@Autowired
	Responses responses;
	
	@Autowired
	UserDao userDao; 
	
	public Object login(User user) {
		System.out.println(user.getEmail()+" ============ "+user.getPassword());
		User u = userDao.findByPassword(user.getEmail(),user.getPassword());
		if(u!=null) {
			return responses.setMsg(u);
		}
		else {
			return responses.setMsg("UserName And Password not match");
		}
	}
	
	public Object signup(User user) {
		User u = userDao.checkByEmail(user.getEmail());
		if(u!=null) {
			return responses.setMsg("error");
		}
		else {
			user.setStatus("false");
			user.setRole("User");
			userDao.insert(user);
			return responses.setMsg(user);
		}
	}
	
	public String generateJwt(String email, String password) {
		Signer signer512 = HMACSigner.newSHA512Signer("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3");
		JWT jwt = new JWT().setIssuer("www.watch-itv.com")
		                   .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
		                   .setSubject("HAHABABABA")
		                   .addClaim("email",email)
		                   .addClaim("password", password)
		                   .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusHours(8));
		return JWT.getEncoder().encode(jwt, signer512);
	}

	public Object allusers() {
		try {
			return responses.setMsg(userDao.findall());
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}

	public Object deleteUser(User u) {
		try {			
			User us = userDao.findById(u.getId());
			userDao.delete(us);
			return responses.setMsg("User Deleted");
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object updateUser(User u) {
		try {
			User user = userDao.findById(u.getId());
			if(u.getRole() != null ) {
				user.setRole( u.getRole());
			}
			if(u.getStatus() != null ) {
				user.setStatus(u.getStatus());
			}
			userDao.update(user);
			return responses.setMsg(user);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	

	
}
