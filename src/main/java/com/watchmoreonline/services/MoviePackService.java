package com.watchmoreonline.services;


import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.watchmoreonline.collection.MovieCollection;
import com.watchmoreonline.collection.MovieCollectionDao;
import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.moviebase.MovieBaseDao;
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.sequals.SequalsDao;

@Service
public class MoviePackService {
	
	@Autowired
	public MovieCollectionDao movieCollectionDao;
	
	@Autowired
	public Responses responses;

	@Autowired
	public SequalsDao sequalsDao;
	
	@Autowired
	public MovieBaseDao movieBaseDao;
	
	public Object newcollection(MovieCollection movieCollection) {
		try {
			movieCollectionDao.insert(movieCollection);
			return responses.setMsg(movieCollection);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object viewmoveiepack() {
		try {
			return responses.setMsg(movieCollectionDao.findAll());
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object getmoviesByMoviePack(MovieCollection movieCollection) {
		try {
			Sequals s = sequalsDao.findByBaseId(movieCollection.getId());
			if(s != null ) {
				String [] list = s.getMovieid();
				List<MovieBase> li = new ArrayList<MovieBase>();
				if(list.length>0) {			
					for(String l:list) {
						MovieBase m = movieBaseDao.find(l);
						li.add(m);
					}
				}
				return responses.setMsg(li);
			}
			else {
				return responses.setMsg("No Sequals of this movie");
			}
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}	
	
	public Object deletepack(MovieCollection movieCollection) {
		try {
			MovieCollection mc = movieCollectionDao.find(movieCollection.getId());
			movieCollectionDao.delete(mc);
			return responses.setMsg("Movie Pack Deleted");
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object updatemoviepackname(MovieCollection movieCollection) {
		try {
			MovieCollection mc = movieCollectionDao.find(movieCollection.getId());
			mc.setName(movieCollection.getName());
			movieCollectionDao.update(mc);
			return responses.setMsg(mc);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}

	public void find() {
		List<MovieCollection> list = movieCollectionDao.findAll();
		for(MovieCollection l:list) {
			Sequals s = sequalsDao.findByBaseId(l.getId());
			if(s != null ) {
				l.setSequence(s.getMovieid().length);
				movieCollectionDao.update(l);
			}
		}
	}
	
}
