package com.watchmoreonline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.watchmoreonline.movies.Movie;
import com.watchmoreonline.movies.MovieDao;
import com.watchmoreonline.users.User;
import com.watchmoreonline.users.UserDao;

@org.springframework.web.bind.annotation.RestController
public class MovieController {
	
	
	@Autowired
	MovieDao mov;
	
	@Autowired
	UserDao udao;
	
//	@GetMapping("/ml")
//	public void ml() {
//		try {
//			
//			File file = new File("C:\\Users\\Dovahkiin\\Desktop\\data\\embed.txt");
//			FileReader fileReader = new FileReader(file);
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			String line;
//			List<String> embed = new  ArrayList<String>();
//			while ((line = bufferedReader.readLine()) != null) {
//				embed.add(line);
//			}
//			fileReader.close();			
//			Collections.reverse(embed); 
//
//			
//			
//			file = new File("C:\\Users\\Dovahkiin\\Desktop\\data\\directlink.txt");
//		    fileReader = new FileReader(file);
//			bufferedReader = new BufferedReader(fileReader);
//			List<String> directlink = new  ArrayList<String>();
//			while ((line = bufferedReader.readLine()) != null) {
//				directlink.add(line);
//			}
//			fileReader.close();		
//			Collections.reverse(directlink); 
//					
//			file = new File("C:\\Users\\Dovahkiin\\Desktop\\data\\size.txt");
//		    fileReader = new FileReader(file);
//			bufferedReader = new BufferedReader(fileReader);
//			List<String> size = new  ArrayList<String>();
//			while ((line = bufferedReader.readLine()) != null) {
//				size.add(line);
//			}
//			fileReader.close();	
//			Collections.reverse(size); 
//			
//			
//			
//			file = new File("C:\\Users\\Dovahkiin\\Desktop\\data\\name.txt");
//		    fileReader = new FileReader(file);
//			bufferedReader = new BufferedReader(fileReader);
//			List<String> name = new  ArrayList<String>();
//			while ((line = bufferedReader.readLine()) != null) {
//				name.add(line);
//			}
//			fileReader.close();	
//			Collections.reverse(name); 
//			
//			
//			
//			file = new File("C:\\Users\\Dovahkiin\\Desktop\\data\\Image.txt");
//		    fileReader = new FileReader(file);
//			bufferedReader = new BufferedReader(fileReader);
//			List<String> image = new  ArrayList<String>();
//			while ((line = bufferedReader.readLine()) != null) {
//				image.add(line);
//			}
//			fileReader.close();	
//			Collections.reverse(image); 
//			
//			
//			Movie m = new Movie();			
//			for(int i=0; i<embed.size(); i++) {
//
//				m = new Movie();
//				m.setDirectlink1080p("OpenLoad");
//				m.setPlay1080p(embed.get(i));
//				m.setDownload1080p(directlink.get(i));
//				m.setSize1080p(size.get(i));
//				m.setName(name.get(i));
//				m.setImage(image.get(i));
//				m.setLanguage("Hindi");
//				m.setStatus("Public");
//				
//				m.setDirectlink480p("");
//				m.setDirectlink720p("");
//	
//				m.setDirectlink4k("");
//				m.setPlay480p("");
//				m.setPlay720p("");
//		
//				m.setPlay4k("");
//				m.setDownload480p("");
//				m.setDownload720p("");
//	
//				m.setDownload4k("");
//				m.setSize480p("");
//				m.setSize720p("");
//
//				m.setSize4k("");
//				mov.insert(m);
//			}
//			
//
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
	
	
	@PostMapping("/login")
	public String login(@RequestBody String data) {
		
		JSONObject json = new JSONObject();	
		try
		{
			JSONParser jp = new JSONParser();		
			JSONObject joObject = (JSONObject)jp.parse(data);
			User u = new User();	
			u.setEmail(joObject.get("Email").toString());
			u = udao.find(u);	
			if( u != null && u.getPassword().equals( joObject.get("Password").toString() ) )
			{	
				json.put("msg", "Success");
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			json.put("msg", "Failure");
		}
		return json.toJSONString();

	}
	
	@PostMapping("/AddMovie")
	public String addmovie(@RequestBody String data) throws ParseException {
		JSONParser jp = new JSONParser();		
		JSONObject json = new JSONObject();	
			JSONObject joObject = (JSONObject)jp.parse(data);	
			Movie m = new Movie();
			m.setName(joObject.get("Name").toString());
			m.setImage(joObject.get("Image").toString());
			m.setDirectlink480p(joObject.get("Directlink480p").toString());
			m.setDirectlink720p(joObject.get("Directlink720p").toString());
			m.setDirectlink1080p(joObject.get("Directlink1080p").toString());
			m.setDirectlink4k(joObject.get("Directlink4k").toString());
			m.setPlay480p(joObject.get("Play480p").toString());
			m.setPlay720p(joObject.get("Play720p").toString());
			m.setPlay1080p(joObject.get("Play1080p").toString());
			m.setPlay4k(joObject.get("Play4k").toString());
			m.setDownload480p(joObject.get("Download480p").toString());
			m.setDownload720p(joObject.get("Download720p").toString());
			m.setDownload1080p(joObject.get("Download1080p").toString());
			m.setDownload4k(joObject.get("Download4k").toString());
			m.setSize480p(joObject.get("Size480p").toString());
			m.setSize720p(joObject.get("Size720p").toString());
			m.setSize1080p(joObject.get("Size1080p").toString());
			m.setSize4k(joObject.get("Size4k").toString());
			m.setLanguage(joObject.get("Language").toString());
			m.setStatus(joObject.get("Status").toString());
			mov.insert(m);
			json.put("msg","Data Saved");
			return json.toJSONString();	
	}
	
	@PostMapping("/getmovie") 
	public JSONArray getmovie(@RequestBody String data) throws ParseException {	
		JSONArray jarr = new JSONArray();
		JSONObject job = new JSONObject();	
		JSONParser jp = new JSONParser();		
		Movie m = mov.find(data);
		job.put("Id",m.getId());		
		job.put("Name",m.getName());
		job.put("Image",m.getImage());
		job.put("Directlink480p",m.getDirectlink480p());
		job.put("Directlink720p",m.getDirectlink720p());
		job.put("Directlink1080p",m.getDirectlink1080p());
		job.put("Directlink4k",m.getDirectlink4k());
		job.put("Play480p",m.getPlay480p());
		job.put("Play720p",m.getPlay720p());
		job.put("Play1080p",m.getPlay1080p());
		job.put("Play4k",m.getPlay4k());
		job.put("Download480p",m.getDownload480p());
		job.put("Download720p",m.getDownload720p());
		job.put("Download1080p",m.getDownload1080p());
		job.put("Download4k",m.getDownload4k());
		job.put("Size480p",m.getSize480p());
		job.put("Size720p",m.getSize720p());
		job.put("Size1080p",m.getSize1080p());
		job.put("Size4k",m.getSize4k());
		job.put("Language",m.getLanguage());
		job.put("Status",m.getStatus());
		jarr.add(job);
		return jarr;	
	}
	
	@GetMapping("/ViewMovies")
	public JSONArray ViewMovies() {	
		JSONArray jarr = new JSONArray();
			for(Movie m : mov.findAll()) {	
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Directlink480p",m.getDirectlink480p());
				job.put("Directlink720p",m.getDirectlink720p());
				job.put("Directlink1080p",m.getDirectlink1080p());
				job.put("Directlink4k",m.getDirectlink4k());
				job.put("Play480p",m.getPlay480p());
				job.put("Play720p",m.getPlay720p());
				job.put("Play1080p",m.getPlay1080p());
				job.put("Play4k",m.getPlay4k());
				job.put("Download480p",m.getDownload480p());
				job.put("Download720p",m.getDownload720p());
				job.put("Download1080p",m.getDownload1080p());
				job.put("Download4k",m.getDownload4k());
				job.put("Size480p",m.getSize480p());
				job.put("Size720p",m.getSize720p());
				job.put("Size1080p",m.getSize1080p());
				job.put("Size4k",m.getSize4k());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				jarr.add(job);
			}	 	
			return jarr;
	}
	
	@GetMapping("/PublicMovies")
	public JSONArray PublicMovies() {	
		JSONArray jarr = new JSONArray();
		for(Movie m : mov.findAll()) {	
			if(m.getStatus().equals("Public")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Directlink480p",m.getDirectlink480p());
				job.put("Directlink720p",m.getDirectlink720p());
				job.put("Directlink1080p",m.getDirectlink1080p());
				job.put("Directlink4k",m.getDirectlink4k());
				job.put("Play480p",m.getPlay480p());
				job.put("Play720p",m.getPlay720p());
				job.put("Play1080p",m.getPlay1080p());
				job.put("Play4k",m.getPlay4k());
				job.put("Download480p",m.getDownload480p());
				job.put("Download720p",m.getDownload720p());
				job.put("Download1080p",m.getDownload1080p());
				job.put("Download4k",m.getDownload4k());
				job.put("Size480p",m.getSize480p());
				job.put("Size720p",m.getSize720p());
				job.put("Size1080p",m.getSize1080p());
				job.put("Size4k",m.getSize4k());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				jarr.add(job);
			}
		}	 	
		return jarr;
	}
	
	
	@GetMapping("/hindiMovies")
	public JSONArray hindiMovies() {	
		JSONArray jarr = new JSONArray();
		for(Movie m : mov.findAll()) {	
			if(m.getStatus().equals("Public") && m.getLanguage().equals("Hindi")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Directlink480p",m.getDirectlink480p());
				job.put("Directlink720p",m.getDirectlink720p());
				job.put("Directlink1080p",m.getDirectlink1080p());
				job.put("Directlink4k",m.getDirectlink4k());
				job.put("Play480p",m.getPlay480p());
				job.put("Play720p",m.getPlay720p());
				job.put("Play1080p",m.getPlay1080p());
				job.put("Play4k",m.getPlay4k());
				job.put("Download480p",m.getDownload480p());
				job.put("Download720p",m.getDownload720p());
				job.put("Download1080p",m.getDownload1080p());
				job.put("Download4k",m.getDownload4k());
				job.put("Size480p",m.getSize480p());
				job.put("Size720p",m.getSize720p());
				job.put("Size1080p",m.getSize1080p());
				job.put("Size4k",m.getSize4k());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				jarr.add(job);
			}
		}	 	
		return jarr;
	}
	
	@GetMapping("/englishMovies")
	public JSONArray englishMovies() {	
		JSONArray jarr = new JSONArray();
		for(Movie m : mov.findAll()) {	
			if(m.getStatus().equals("Public") && m.getLanguage().equals("English") ) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Directlink480p",m.getDirectlink480p());
				job.put("Directlink720p",m.getDirectlink720p());
				job.put("Directlink1080p",m.getDirectlink1080p());
				job.put("Directlink4k",m.getDirectlink4k());
				job.put("Play480p",m.getPlay480p());
				job.put("Play720p",m.getPlay720p());
				job.put("Play1080p",m.getPlay1080p());
				job.put("Play4k",m.getPlay4k());
				job.put("Download480p",m.getDownload480p());
				job.put("Download720p",m.getDownload720p());
				job.put("Download1080p",m.getDownload1080p());
				job.put("Download4k",m.getDownload4k());
				job.put("Size480p",m.getSize480p());
				job.put("Size720p",m.getSize720p());
				job.put("Size1080p",m.getSize1080p());
				job.put("Size4k",m.getSize4k());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				jarr.add(job);
			}
		}	 	
		return jarr;
	}
	
	
	
	@GetMapping("/PrivateMovies")
	public JSONArray PrivateMovies() {	
		JSONArray jarr = new JSONArray();
		for(Movie m : mov.findAll()) {	
			if(m.getStatus().equals("Private")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Directlink480p",m.getDirectlink480p());
				job.put("Directlink720p",m.getDirectlink720p());
				job.put("Directlink1080p",m.getDirectlink1080p());
				job.put("Directlink4k",m.getDirectlink4k());
				job.put("Play480p",m.getPlay480p());
				job.put("Play720p",m.getPlay720p());
				job.put("Play1080p",m.getPlay1080p());
				job.put("Play4k",m.getPlay4k());
				job.put("Download480p",m.getDownload480p());
				job.put("Download720p",m.getDownload720p());
				job.put("Download1080p",m.getDownload1080p());
				job.put("Download4k",m.getDownload4k());
				job.put("Size480p",m.getSize480p());
				job.put("Size720p",m.getSize720p());
				job.put("Size1080p",m.getSize1080p());
				job.put("Size4k",m.getSize4k());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				jarr.add(job);
			}
		}	 	
		return jarr;
	}
	
	@PostMapping("/UpdateMovie")
	public String UpdateMovie(@RequestBody String data) throws ParseException {	
		JSONObject json = new JSONObject();	
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		Movie m = mov.find(joObject.get("Id").toString());
		m.setName(joObject.get("Name").toString());
		m.setImage(joObject.get("Image").toString());
		m.setDirectlink480p(joObject.get("Directlink480p").toString());
		m.setDirectlink720p(joObject.get("Directlink720p").toString());
		m.setDirectlink1080p(joObject.get("Directlink1080p").toString());
		m.setDirectlink4k(joObject.get("Directlink4k").toString());
		m.setPlay480p(joObject.get("Play480p").toString());
		m.setPlay720p(joObject.get("Play720p").toString());
		m.setPlay1080p(joObject.get("Play1080p").toString());
		m.setPlay4k(joObject.get("Play4k").toString());
		m.setDownload480p(joObject.get("Download480p").toString());
		m.setDownload720p(joObject.get("Download720p").toString());
		m.setDownload1080p(joObject.get("Download1080p").toString());
		m.setDownload4k(joObject.get("Download4k").toString());
		m.setSize480p(joObject.get("Size480p").toString());
		m.setSize720p(joObject.get("Size720p").toString());
		m.setSize1080p(joObject.get("Size1080p").toString());
		m.setSize4k(joObject.get("Size4k").toString());
		m.setLanguage(joObject.get("Language").toString());
		m.setStatus(joObject.get("Status").toString());
		mov.update(m);
		json.put("msg","Data Update");
		return json.toJSONString();		
	}
	
	
	@PostMapping("/deleteselected")
	public  String deleteselected(@RequestBody String[] data) throws ParseException {	
		JSONObject json = new JSONObject();	
		
		for(String d:data) {
			Movie m = mov.find(d);
			mov.delete(m);
		}
		
		json.put("msg","Data Deleted");
		return json.toJSONString();
	}
	
	@PostMapping("/DeleteMovie")
	public  String DeleteMovie(@RequestBody String data) throws ParseException {	
		JSONObject json = new JSONObject();	
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		Movie m = mov.find(joObject.get("Id").toString());
		mov.delete(m);
		json.put("msg","Data Deleted");
		return json.toJSONString();
	}
}
