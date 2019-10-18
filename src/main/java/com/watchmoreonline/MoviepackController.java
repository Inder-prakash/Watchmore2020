package com.watchmoreonline;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.watchmoreonline.collection.MovieCollection;
import com.watchmoreonline.collection.MovieCollectionDao;
import com.watchmoreonline.movies.Movie;
import com.watchmoreonline.movies.MovieDao;
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.sequals.SequalsDao;

@org.springframework.web.bind.annotation.RestController
public class MoviepackController {
	
	@Autowired
	public MovieCollectionDao mov;
	@Autowired
	SequalsDao sdao;
	@Autowired
	MovieDao move;
	
	@PostMapping("/newcollection")
	public String newcollection(@RequestBody String data ) throws ParseException {
		JSONObject json = new JSONObject();	
		MovieCollection m = new MovieCollection();
		m.setName(data);	
		mov.insert(m);
		json.put("msg", "done");
		return json.toJSONString();
	}
	
	@GetMapping("/viewmoveiepack")
	public JSONArray viewmoveiepack() {	
		JSONArray jarr = new JSONArray();
			for(MovieCollection m : mov.findAll()) {	
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				jarr.add(job);
			}	 	
			return jarr;
	}
	
	@GetMapping("/managemoveiepack")
	public JSONArray managemoveiepack() {	
		JSONArray jarr = new JSONArray();
		for(MovieCollection m : mov.findAll()) {	
			JSONObject job = new JSONObject();	
			job.put("Id",m.getId());		
			job.put("Name",m.getName());
			
			Sequals s= sdao.find(m.getId());
			if(s != null ) {
				job.put("Sequals",s.getMovieid().size());
			}
			else {
				job.put("Sequals","0");
			}
			
			jarr.add(job);
		}	
		return jarr;
	}
	
	
	@PostMapping("/getmovies")
	public JSONArray getmovies(@RequestBody String data) {	
		JSONArray jarr = new JSONArray();
		JSONObject job = new JSONObject();	
		Sequals s = sdao.find(data);
		if( s != null ) {
			List<String> moviename = s.getMovieid();
			for(String ms:moviename) {
				job = new JSONObject();		
				Movie m = move.find(ms);
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				jarr.add(job);	
			}
		}
		
		return jarr;	
	}
	
	@PostMapping("/updatemoviepackname")
	public String updatemoviepackname(@RequestBody String data) throws ParseException {	
		JSONParser jp = new JSONParser();		
		JSONArray ja = (JSONArray)jp.parse(data);	
		JSONObject json = new JSONObject();	
		String id = ja.get(0).toString();
		String newname = ja.get(1).toString();
		MovieCollection m = mov.find(id);
		m.setName(newname);
		mov.update(m);
		json.put("msg", "update");
		return json.toJSONString();		
	}
	
	@PostMapping("/deletepack")
	public String deletepack(@RequestBody String data) throws ParseException {	
		JSONObject json = new JSONObject();		
		MovieCollection m = mov.find(data);
		mov.delete(m);
		Sequals s= sdao.find(data);
		if(s != null ) {
			sdao.delete(s);
		}
		json.put("msg","Data Deleted");
		return json.toJSONString();	
	}
	
	
}
