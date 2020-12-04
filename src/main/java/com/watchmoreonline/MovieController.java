package com.watchmoreonline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.moviebase.MovieBaseDao;
import com.watchmoreonline.users.User;
import com.watchmoreonline.users.UserDao;

@org.springframework.web.bind.annotation.RestController
public class MovieController {
	
	
	@Autowired
	MovieBaseDao mbd;
	
	@Autowired
	UserDao udao;
	
	public String upload(HttpServletRequest req , String imageUrl) {
		try {
			String path2 = req.getRealPath("/");
			String path = path2+"temp.jpg";
			saveImage(imageUrl, path);
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                  	"cloud_name", "df3btb7v4",
                 	"api_key", "144397625466591",
                 	"api_secret", "_auyDn69x8adsIwxVwMRrljcx0U"));
			File f = new File(path);
			Map uploadResult = cloudinary.uploader().upload(f,ObjectUtils.emptyMap());
			return uploadResult.get("secure_url").toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
		
	}
	
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);
		byte[] b = new byte[2048];
		int length;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		is.close();
		os.close();
	}
	
	
	@PostMapping("/AddMovie")
	public String addmovie(HttpServletRequest req ,@RequestBody String data) throws ParseException {
		JSONParser jp = new JSONParser();		
		JSONObject json = new JSONObject();	
			JSONObject joObject = (JSONObject)jp.parse(data);	
			MovieBase m = new MovieBase();
			m.setName(joObject.get("Name").toString());
			m.setImage(upload(req, joObject.get("Image").toString()));
			m.setLanguage(joObject.get("Language").toString());
			m.setLink(joObject.get("Link").toString());
			m.setSize(joObject.get("Size").toString());
			m.setStatus(joObject.get("Status").toString());
			m.setGenere(joObject.get("Genere").toString());
			m.setDiscription(joObject.get("Discription").toString());
			mbd.insert(m);
			json.put("msg","Data Saved");
			return json.toJSONString();	
	}
	
	@PostMapping("/getmovie") 
	public JSONArray getmovie(@RequestBody String data) throws ParseException {	
		JSONArray jarr = new JSONArray();
		JSONObject job = new JSONObject();	
		JSONParser jp = new JSONParser();		
		MovieBase m = mbd.find(data);
		job.put("Id",m.getId());		
		job.put("Name",m.getName());
		job.put("Image",m.getImage());
		job.put("Language",m.getLanguage());
		job.put("Status",m.getStatus());
		job.put("Link",m.getLink());
		job.put("Discription",m.getDiscription());
		job.put("Genere",m.getGenere());
		job.put("Size",m.getSize());
		jarr.add(job);
		return jarr;	
	}
	
	@GetMapping("/ViewMovies")
	public JSONArray ViewMovies() throws IOException {	
		JSONArray jarr = new JSONArray();
			for(MovieBase m : mbd.findAll()) {	
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Link",m.getLink());
				job.put("Discription",m.getDiscription());
				job.put("Genere",m.getGenere());
				job.put("Size",m.getSize());
//				saveImage(m.getImage(), "E:\\Movies\\"+job.put("Id",m.getId())+".jpg");
				System.out.println();
				jarr.add(job);
			}	 	
			return jarr;
	}
	
	@GetMapping("/PublicMovies")
	public JSONArray PublicMovies() {	
		JSONArray jarr = new JSONArray();
		for(MovieBase m : mbd.findAll()) {	
			if(m.getStatus().equals("Public")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Link",m.getLink());
				job.put("Discription",m.getDiscription());
				job.put("Genere",m.getGenere());
				job.put("Size",m.getSize());
				jarr.add(job);
			}
		}	 	
		return jarr;
	}
	
	

	public JSONArray getingmovies (String Status , String Language) {
		JSONArray jarr = new JSONArray();
		for(MovieBase m : mbd.findAll()) {	
			if(m.getStatus().equals(Status) && m.getLanguage().equals(Language)) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Link",m.getLink());
				job.put("Discription",m.getDiscription());
				job.put("Genere",m.getGenere());
				job.put("Size",m.getSize());
				jarr.add(job);
			}
		}
		return jarr;
	}
	
	public JSONArray getingmoviesbycat (String Status , String Genere) {
		JSONArray jarr = new JSONArray();
		for(MovieBase m : mbd.findAll()) {	
			if(m.getStatus().equals(Status) && m.getGenere().equals(Genere)) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Link",m.getLink());
				job.put("Discription",m.getDiscription());
				job.put("Genere",m.getGenere());
				job.put("Size",m.getSize());
				jarr.add(job);
			}
		}
		return jarr;
	}
	
	@GetMapping("/hindiMovies")
	public JSONArray hindiMovies() {	
		return getingmovies("Public","Hindi");
	}
	
	@GetMapping("/englishMovies")
	public JSONArray englishMovies() {	
		return getingmovies("Public","English");
	}
	
	@GetMapping("/CrimeMovies")
	public JSONArray CrimeMovies() {	
		return getingmoviesbycat("Public","Crime");
	}
	
	@GetMapping("/ComedyMovies")
	public JSONArray ComedyMovies() {	
		return getingmoviesbycat("Public","Comedy");
	}
	
	@GetMapping("/SciFiMovies")
	public JSONArray SciFiMovies() {	
		return getingmoviesbycat("Public","Sci-Fi");
	}
	
	@GetMapping("/HorrorMovies")
	public JSONArray HorrorMovies() {	
		return getingmoviesbycat("Public","Horror");
	}
	
	@GetMapping("/RomanceMovies")
	public JSONArray RomanceMovies() {	
		return getingmoviesbycat("Public","Romance");
	}
	
	@GetMapping("/ActionMovies")
	public JSONArray ActionMovies() {	
		return getingmoviesbycat("Public","Action");
	}
	
	@GetMapping("/DramaMovies")
	public JSONArray DramaMovies() {	
		return getingmoviesbycat("Public","Drama");
	}
	
	@GetMapping("/AnimationMovies")
	public JSONArray AnimationMovies() {	
		return getingmoviesbycat("Public","Animation");
	}
	
	@GetMapping("/AdventureMovies")
	public JSONArray AdventureMovies() {	
		return getingmoviesbycat("Public","Adventure");
	}
	
	@GetMapping("/PrivateMovies")
	public JSONArray PrivateMovies() {	
		JSONArray jarr = new JSONArray();
		for(MovieBase m : mbd.findAll()) {	
			if(m.getStatus().equals("Private")) {
				JSONObject job = new JSONObject();	
				job.put("Id",m.getId());		
				job.put("Name",m.getName());
				job.put("Image",m.getImage());
				job.put("Language",m.getLanguage());
				job.put("Status",m.getStatus());
				job.put("Link",m.getLink());
				job.put("Discription",m.getDiscription());
				job.put("Genere",m.getGenere());
				job.put("Size",m.getSize());
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
		MovieBase m = mbd.find(joObject.get("Id").toString());	
		m.setName(joObject.get("Name").toString());
		m.setImage(joObject.get("Image").toString());
		m.setLanguage(joObject.get("Language").toString());
		m.setLink(joObject.get("Link").toString());
		m.setSize(joObject.get("Size").toString());
		m.setStatus(joObject.get("Status").toString());
		m.setGenere(joObject.get("Genere").toString());
		m.setDiscription(joObject.get("Discription").toString());
		mbd.update(m);
		json.put("msg","Data Update");
		return json.toJSONString();		
	}
	
	
	@PostMapping("/deleteselected")
	public  String deleteselected(@RequestBody String[] data) throws ParseException {	
		JSONObject json = new JSONObject();	
		
		for(String d:data) {
			MovieBase m = mbd.find(d);
			mbd.delete(m);
		}
		
		json.put("msg","Data Deleted");
		return json.toJSONString();
	}
	
	@PostMapping("/DeleteMovie")
	public  String DeleteMovie(@RequestBody String data) throws ParseException {	
		JSONObject json = new JSONObject();	
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);
		MovieBase m = mbd.find(joObject.get("Id").toString());
		mbd.delete(m);
		json.put("msg","Data Deleted");
		return json.toJSONString();
	}
}
