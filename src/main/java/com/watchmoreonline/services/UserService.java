package com.watchmoreonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchmoreonline.users.User;

@Service
public class UserService {
	
	@Autowired
	Responses responses;
	
	public Object login(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		return responses.setMsg(user.getUsername()+" = = "+user.getPassword());
	}
	
}
