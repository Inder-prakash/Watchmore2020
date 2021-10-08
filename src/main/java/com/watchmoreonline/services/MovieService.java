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
			List<MovieBase> m = movieBaseDao.movieByCategories(movie);
			return responses.setMsg2(m,m.size());
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object movieByLanguage(MovieBase movie) {
		try {
			List<MovieBase> m = movieBaseDao.movieByLanguage(movie);
			return responses.setMsg2(m,m.size());
		}
		catch (Exception e) {
			return responses.setMsg(e);
		}
	}
	
	public Object updateMovies(MovieBase movie) {
		try {
			MovieBase m = movieBaseDao.find(movie.getId());
			m.setName(movie.getName());
			m.setImage(movie.getImage());
			m.setLanguage(movie.getLanguage());
			m.setLink(movie.getLink());
			m.setSize(movie.getSize());
			m.setStatus(movie.getStatus());
			m.setGenere(movie.getGenere());
			m.setDiscription(movie.getDiscription());
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
