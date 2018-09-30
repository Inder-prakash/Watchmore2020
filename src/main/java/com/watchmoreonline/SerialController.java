package com.watchmoreonline;

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
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.serials.Serial;
import com.watchmoreonline.serials.SerialDao;

@org.springframework.web.bind.annotation.RestController
public class SerialController {

	@Autowired
	SerialDao sd;
	

	@PostMapping("/addserial")
	public String addserial(@RequestBody String data ) throws ParseException {
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		JSONObject json = new JSONObject();
		Serial s = new Serial();
		s.setName(joObject.get("Name").toString());
		s.setImage(joObject.get("Image").toString());
		s.setStatus(joObject.get("Status").toString());	
		s.setLanguage(joObject.get("Language").toString());	
		List<String> ename = new ArrayList<String>();
		List<String> elink = new ArrayList<String>();		
		JSONArray ja = (JSONArray)jp.parse(joObject.get("Episodes").toString());		
		for(int i = 0; i < ja.size() ;i++) {
			json = (JSONObject) jp.parse(ja.get(i).toString());
			ename.add(json.get("ename").toString());
			elink.add(json.get("elink").toString());
	    }		
		s.setEname(ename);
		s.setElink(elink);
		sd.insert(s);
		json.put("msg", "done");
		return json.toJSONString();
	}
	
	@GetMapping("/viewtv")
	public JSONArray viewtv() {	
		JSONArray jarr = new JSONArray();
		for(Serial m : sd.findAll()) {	
			JSONObject job = new JSONObject();	
			job.put("Id",m.getId());		
			job.put("Name",m.getName());
			job.put("Status",m.getStatus());
			job.put("Image",m.getImage());
			job.put("Language",m.getLanguage());
			job.put("Total",m.getEname().size());
			jarr.add(job);
		}	
		System.out.println(jarr);
		return jarr;
	}
	
	
	@PostMapping("/gettv")
	public JSONArray gettv(@RequestBody String data) {	
		JSONArray jarr = new JSONArray();
		Serial m = sd.find(data);
		JSONObject job = new JSONObject();	
		job.put("Id",m.getId());		
		job.put("Name",m.getName());
		job.put("Image",m.getImage());	
		JSONArray ep = new JSONArray();
		for(int i= 0; i<m.getElink().size(); i++) {
			JSONObject j = new JSONObject();	
			j.put("ename", m.getEname().get(i));
			j.put("elink", m.getElink().get(i));
			ep.add(j);
		}		
		job.put("Episodes",ep);	
		jarr.add(job);	
		return jarr;
	}
	
	@GetMapping("/publictv")
	public JSONArray publictv() {	
		JSONArray jarr = new JSONArray();
		for(Serial m : sd.findAll()) {
			if(m.getStatus().equals("Public")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Status",m.getStatus());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Total",m.getEname().size());
				jarr.add(job);
			}
		}	
		System.out.println(jarr);
		return jarr;
	}
	 
	
	
	@PostMapping("/getsepisodes")
	public JSONArray getsepisodes(@RequestBody String data) {	
		JSONArray jarr = new JSONArray();
		JSONObject job = new JSONObject();	
		Serial s = sd.find(data);
		if(s != null) {			
			for(int i= 0; i<s.getElink().size(); i++) {
				job = new JSONObject();
				job.put("ename", s.getEname().get(i));
				job.put("elink", s.getElink().get(i));
				jarr.add(job);
			}
		}	
		return jarr;	
	}
	
	@PostMapping("/updatepisodes")
	public String updatepisodes(@RequestBody String data ) throws ParseException {
		
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		JSONObject json = new JSONObject();
		Serial s = sd.find(joObject.get("Id").toString());
		List<String> ename = new ArrayList<String>();
		List<String> elink = new ArrayList<String>();		
		JSONArray ja = (JSONArray)jp.parse(joObject.get("Episodes").toString());		
		for(int i = 0; i < ja.size() ;i++) {
			json = (JSONObject) jp.parse(ja.get(i).toString());
			ename.add(json.get("ename").toString());
			elink.add(json.get("elink").toString());
	    }		
		s.setEname(ename);
		s.setElink(elink);
		sd.update(s);
		json.put("msg", "done");
		return json.toJSONString();	
	}
	
	@PostMapping("/updatetv")
	public String updatetv(@RequestBody String data ) throws ParseException {
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		JSONObject json = new JSONObject();
		Serial s = sd.find(joObject.get("Id").toString());
		s.setName(joObject.get("Name").toString());
		s.setImage(joObject.get("Image").toString());
		s.setStatus(joObject.get("Status").toString());	
		s.setLanguage(joObject.get("Language").toString());
		sd.update(s);
		json.put("msg", "done");
		return json.toJSONString();	
	}
	
	
	@PostMapping("/deletetv")
	public String deletetv(@RequestBody String data ) {
		JSONObject json = new JSONObject();
		Serial s = sd.find(data);
		sd.delete(s);
		json.put("msg", "done");
		return json.toJSONString();	
	}
	
	
	
	
}
