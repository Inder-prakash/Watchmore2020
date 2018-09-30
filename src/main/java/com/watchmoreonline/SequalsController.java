package com.watchmoreonline;

import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.watchmoreonline.collection.MovieCollection;
import com.watchmoreonline.movies.Movie;
import com.watchmoreonline.movies.MovieDao;
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.sequals.SequalsDao;

@org.springframework.web.bind.annotation.RestController
public class SequalsController {


	@Autowired
	SequalsDao sdao;

	@Autowired
	MovieDao mov;
	
	@PostMapping("/newsequals")
	public String newsequals(@RequestBody String data ) throws ParseException {
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		JSONObject json = new JSONObject();
		JSONArray ja = (JSONArray)jp.parse(joObject.get("seuals").toString());		
		Sequals s = new Sequals();
		s.setBaseid(joObject.get("Baseid").toString());
		List<String> squalsname = s.getMovieid();
		for(int i = 0; i < ja.size() ;i++) {
			json = (JSONObject) jp.parse(ja.get(i).toString());
			squalsname.add(json.get("name").toString());
	    }	
		s.setMovieid(squalsname);
		sdao.insert(s);
		json.put("msg", "done");
		return json.toJSONString();
	}
	
	@PostMapping("/getsequals")
	public JSONArray getsequals(@RequestBody String data) {	
		JSONArray jarr = new JSONArray();
		JSONObject job = new JSONObject();	
		Movie m = new Movie();
		Sequals s = sdao.find(data);
		if(s != null) {
		List<String> moviename = s.getMovieid();
			for(String ms:moviename) {
				job = new JSONObject();			
				job.put("id",ms);	
				m = mov.find(ms);
				if(m != null) {
					job.put("name", m.getName());
					job.put("image", m.getImage());
				}

				jarr.add(job);	
			}
		}	
		return jarr;	
	}
	

	@PostMapping("/udatesequal")
	public String udatesequal(@RequestBody String data ) throws ParseException {
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		JSONObject json = new JSONObject();	
		JSONArray ja = (JSONArray)jp.parse(joObject.get("seuals").toString());		
		Sequals s = sdao.find(joObject.get("Baseid").toString());
		if(s != null) {
			List<String> squalsname = new ArrayList<String>();
			for(int i = 0; i < ja.size() ;i++) {
				json = (JSONObject) jp.parse(ja.get(i).toString());
				squalsname.add(json.get("id").toString());
		    }
			s.setMovieid(squalsname);
			sdao.update(s);
		}
		else {
			s = new Sequals();
			s.setBaseid(joObject.get("Baseid").toString());
			List<String> squalsname = s.getMovieid();
			for(int i = 0; i < ja.size() ;i++) {
				json = (JSONObject) jp.parse(ja.get(i).toString());
				squalsname.add(json.get("id").toString());
		    }	
			s.setMovieid(squalsname);
			sdao.insert(s);
		}
		
		
		json.put("msg", "done");
		return json.toJSONString();
	}
	
	
	
	
}
