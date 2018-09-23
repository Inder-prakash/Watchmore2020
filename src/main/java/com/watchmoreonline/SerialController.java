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

import com.watchmoreonline.movies.Movie;
import com.watchmoreonline.tvseries.Serial;
import com.watchmoreonline.tvseries.SerialDao;

@org.springframework.web.bind.annotation.RestController
public class SerialController {

	@Autowired
	SerialDao sdao;
	
	@PostMapping("/addtv")
	public String addtv(@RequestBody String data) throws ParseException {
		JSONParser jp = new JSONParser();		
		JSONObject json = new JSONObject();	
		JSONObject joObject = (JSONObject)jp.parse(data);	
		Serial s = new Serial();
		s.setName(joObject.get("Name").toString());
		s.setImage(joObject.get("Image").toString());
		s.setStatus(joObject.get("Status").toString());
		s.setLanguage(joObject.get("Language").toString());
//		List<String> episodes = s.getEpisodes();
//		episodes.add(joObject.get("Episodes").toString());
//		s.setEpisodes(episodes);
		sdao.insert(s);
		json.put("msg","Data Saved");
		return json.toJSONString();	
	}
	
	@PostMapping("/gettv") 
	public JSONArray gettv(@RequestBody String data) throws ParseException {	
		JSONArray jarr = new JSONArray();
		JSONObject job = new JSONObject();	
		Serial m = sdao.find(data);
		job.put("Id",m.getId());		
		job.put("Name",m.getName());
		job.put("Image",m.getImage());
		job.put("Language",m.getLanguage());
		job.put("Status",m.getStatus());
		job.put("Episode", m.getEpisodes());
		jarr.add(job);
		return jarr;
		
	}
	
	@GetMapping("/Viewtv")
	public JSONArray Viewtv() {	
		JSONArray jarr = new JSONArray();
			for(Serial m : sdao.findAll()) {	
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Episode", m.getEpisodes());
				jarr.add(job);
			}	 	
			return jarr;
	}
	
	@GetMapping("/Publictv")
	public JSONArray Publictv() {	
		JSONArray jarr = new JSONArray();
		for(Serial m : sdao.findAll()) {
			if(m.getStatus().equals("Public")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Episode", m.getEpisodes());
				jarr.add(job);
			}
		}		 	
		return jarr;
	}
	
	@GetMapping("/Privatetv")
	public JSONArray Privatetv() {	
		JSONArray jarr = new JSONArray();
		for(Serial m : sdao.findAll()) {
			if(m.getStatus().equals("Private")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Episode", m.getEpisodes());
				jarr.add(job);
			}
		}		 	
		return jarr; 	
	}
	
	@PostMapping("/Updatetv")
	public String Updatetv(@RequestBody String data) throws ParseException {	
		JSONObject json = new JSONObject();	
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		Serial m = sdao.find(joObject.get("Id").toString());
		m.setName(joObject.get("Name").toString());
		m.setImage(joObject.get("Image").toString());
		m.setLanguage(joObject.get("Language").toString());
		m.setStatus(joObject.get("Status").toString());
		sdao.update(m);
		json.put("msg","Data Update");
		return json.toJSONString();		
	}
	
	@PostMapping("/Deletetv")
	public  String Deletetv(@RequestBody String data) throws ParseException {	
		JSONObject json = new JSONObject();		
		Serial m = sdao.find(data);
		sdao.delete(m);
		json.put("msg","Data Deleted");
		return json.toJSONString();
	}
	
	
}

