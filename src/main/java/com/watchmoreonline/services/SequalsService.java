package com.watchmoreonline.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchmoreonline.collection.MovieCollection;
import com.watchmoreonline.collection.MovieCollectionDao;
import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.moviebase.MovieBaseDao;
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.sequals.SequalsDao;

@Service
public class SequalsService {
	
	@Autowired
	SequalsDao sequalsDao;
	
	@Autowired
	Responses responses;
	
	@Autowired
	MovieBaseDao movieBaseDao;
	
	@Autowired 
	MovieCollectionDao movieCollectionDao;
	
	public Object newsequals(Sequals sequals) {
		try {
			sequalsDao.insert(sequals);
			MovieCollection mc = movieCollectionDao.find(sequals.getBaseid());
			System.out.println(sequals.getMovieid().length);
			mc.setSequence(sequals.getMovieid().length);
			movieCollectionDao.update(mc);
			return responses.setMsg(sequals);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
		
	}
	
	public Object getsequals(Sequals sequals) {
		try {
			Sequals s = sequalsDao.findByBaseId(sequals.getBaseid());
			if(s != null ) {
				String[] list = s.getMovieid();
				List<MovieBase> li = new ArrayList<MovieBase>();
				if(list.length> 0) {			
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
	
	public Object udatesequal(Sequals sequals) {
		try {
			System.out.println("sdfsdf");
			Sequals s = sequalsDao.findByBaseId(sequals.getBaseid());
			s.setMovieid(sequals.getMovieid());
			sequalsDao.update(s);
			MovieCollection mc = movieCollectionDao.find(sequals.getBaseid());
			mc.setSequence(sequals.getMovieid().length);
			movieCollectionDao.update(mc);
			return responses.setMsg(s);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
		
	}
	
	
//	@PostMapping("/newsequals")
//	public String newsequals(@RequestBody String data ) throws ParseException {
//		JSONParser jp = new JSONParser();		
//		JSONObject joObject = (JSONObject)jp.parse(data);
//		JSONObject json = new JSONObject();
//		
//		JSONArray ja = (JSONArray)jp.parse(joObject.get("seuals").toString());	
//		
//		Sequals s = new Sequals();
//		s.setBaseid(joObject.get("Baseid").toString());
//		List<String> squalsname = s.getMovieid();
//		for(int i = 0; i < ja.size() ;i++) {
//			json = (JSONObject) jp.parse(ja.get(i).toString());
//			squalsname.add(json.get("name").toString());
//	    }	
//		s.setMovieid(squalsname);
//		sdao.insert(s);
//		json.put("msg", "done");
//		return json.toJSONString();
//	}
	
//	@PostMapping("/getsequals")
//	public JSONArray getsequals(@RequestBody String data) {	
//		JSONArray jarr = new JSONArray();
//		JSONObject job = new JSONObject();	
//		MovieBase m = new MovieBase();
//		Sequals s = sdao.find(data);
//		if(s != null) {
//		List<String> moviename = s.getMovieid();
//			for(String ms:moviename) {
//				job = new JSONObject();			
//				job.put("id",ms);	
//				m = mov.find(ms);
//				if(m != null) {
//					job.put("name", m.getName());
//					job.put("image", m.getImage());
//				}
//
//				jarr.add(job);	
//			}
//		}	
//		return jarr;	
//	}
	

//	@PostMapping("/udatesequal")
//	public String udatesequal(@RequestBody String data ) throws ParseException {
//		JSONParser jp = new JSONParser();		
//		JSONObject joObject = (JSONObject)jp.parse(data);
//		JSONObject json = new JSONObject();	
//		JSONArray ja = (JSONArray)jp.parse(joObject.get("seuals").toString());		
//		Sequals s = sdao.find(joObject.get("Baseid").toString());
//		if(s != null) {
//			List<String> squalsname = new ArrayList<String>();
//			for(int i = 0; i < ja.size() ;i++) {
//				json = (JSONObject) jp.parse(ja.get(i).toString());
//				squalsname.add(json.get("id").toString());
//		    }
//			s.setMovieid(squalsname);
//			sdao.update(s);
//		}
//		else {
//			s = new Sequals();
//			s.setBaseid(joObject.get("Baseid").toString());
//			List<String> squalsname = s.getMovieid();
//			for(int i = 0; i < ja.size() ;i++) {
//				json = (JSONObject) jp.parse(ja.get(i).toString());
//				squalsname.add(json.get("id").toString());
//		    }	
//			s.setMovieid(squalsname);
//			sdao.insert(s);
//		}
//		
//		
//		json.put("msg", "done");
//		return json.toJSONString();
//	}
}
