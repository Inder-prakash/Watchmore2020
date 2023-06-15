package com.watchmoreonline.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.moviebase.MovieBaseDao;

@Service
public class MovieService {

	@Autowired
	MovieBaseDao movieBaseDao;
	
	@Autowired
	Responses responses;
	
	@Autowired
	 SerialService serialService;
	
	public Object addmovie(MovieBase movie) {
		try {
			movieBaseDao.insert(movie);
			return responses.setMsg(movie);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object getmovie(MovieBase movie) {
		try {
			MovieBase m = movieBaseDao.find(movie.getId());
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

	public Object viewmovies(MovieBase movie) {
		try {
			return movieBaseDao.findAll(movie);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object movieByStatus(MovieBase movie) {
		try {
			return movieBaseDao.movieByStatus(movie);
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object movieByCategories(MovieBase movie) {
		try {
			return movieBaseDao.movieByCategories(movie);
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object movieByLanguage(MovieBase movie) {
		try {
			return movieBaseDao.movieByLanguage(movie);
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object updateMovies(MovieBase movie) {
		try {
			MovieBase m = movieBaseDao.find(movie.getId());
			if(movie.getName() != null) {
				m.setName(movie.getName());		
			}
			if(movie.getImage() != null) {
				m.setImage(movie.getImage());
			}					
			if(movie.getLanguage() != null) {
				m.setLanguage(movie.getLanguage());	
			}
			if(movie.getLink() != null) {
				m.setLink(movie.getLink());
			}
			if(movie.getSize() != null) {
				m.setSize(movie.getSize());
			}
			if(movie.getStatus() != null) {
				m.setStatus(movie.getStatus());
			}
			if(movie.getGenere() != null) {
				m.setGenere(movie.getGenere());
			}
			movieBaseDao.update(m);
			return responses.setMsg(m);
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object deleteselected(String [] movieIds) {
		try {
			for(String mId:movieIds) {
				MovieBase m = movieBaseDao.find(mId);
				movieBaseDao.delete(m);
			}			
			return responses.setMsg("Record deleted");
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object search(Integer page ,String text , String cat) {
		if(cat.equalsIgnoreCase("movie")) {
			return movieBaseDao.search(page,text); 
		}
		else {
			return serialService.Serialsearch(page,text); 
		}	
	}
	
	public Object search2(Integer page ,String text , String cat,String admin) {
		if(cat.equalsIgnoreCase("movie")) {
			return movieBaseDao.search2(page,text); 
		}
		else {
			return serialService.Serialsearch2(page,text); 
		}	
	}
	
//	public String upload(HttpServletRequest req , String imageUrl) {
//		try {
//			String path2 = req.getRealPath("/");
//			String path = path2+"temp.jpg";
//			saveImage(imageUrl, path);
//			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                  	"cloud_name", "df3btb7v4",
//                 	"api_key", "144397625466591",
//                 	"api_secret", "_auyDn69x8adsIwxVwMRrljcx0U"));
//			File f = new File(path);
//			Map uploadResult = cloudinary.uploader().upload(f,ObjectUtils.emptyMap());
//			return uploadResult.get("secure_url").toString();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return "Failed";
//		}
//		
//	}
//	
//	
//	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
//		URL url = new URL(imageUrl);
//		InputStream is = url.openStream();
//		OutputStream os = new FileOutputStream(destinationFile);
//		byte[] b = new byte[2048];
//		int length;
//		while ((length = is.read(b)) != -1) {
//			os.write(b, 0, length);
//		}
//		is.close();
//		os.close();
//	}
}
