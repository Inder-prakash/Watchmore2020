package com.watchmoreonline.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.moviebase.MovieBaseDao;

@Service
public class MovieService {

//	@Autowired
//	MovieBase movieBase;
//	
	@Autowired
	MovieBaseDao movieBaseDao;
	
	@Autowired
	Responses responses;
	
	public Object addmovie(MovieBase movie) {
		try {
			MovieBase m = new MovieBase();
			movieBaseDao.insert(movie);
			return responses.setMsg(m);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object getmovie(String userId) {
		try {
			MovieBase m = movieBaseDao.find(userId);
			if(m != null ) {
				return responses.setMsg(m);
			}
			else {
				return responses.setMsg("Record Not Found");
			}
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}

	public Object viewmovies() {
		try {
			return responses.setMsg(movieBaseDao.findAll());
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object publicmovies() {
		try {
			return responses.setMsg(movieBaseDao.publicmovies());
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object privatemovies() {
		try {
			return responses.setMsg(movieBaseDao.privatemovies());
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
}
