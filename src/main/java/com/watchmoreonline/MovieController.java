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
import com.watchmoreonline.services.MovieService;
import com.watchmoreonline.users.User;
import com.watchmoreonline.users.UserDao;

import springfox.documentation.spring.web.json.Json;

@org.springframework.web.bind.annotation.RestController
public class MovieController {
	
	
	@Autowired
	MovieBaseDao mbd;
	
	@Autowired
	UserDao udao;
	
	@Autowired
	MovieService movieService;
	
	@PostMapping("/AddMoive")
	public Object addmovie(@RequestBody MovieBase movie) {
		return movieService.addmovie(movie);
	}
	
	@PostMapping("/getmovie")
	public Object getmovie(@RequestBody MovieBase movie) {
		return movieService.getmovie(movie);
	}

	@PostMapping("/ViewMovies")
	public Object viewmovies(@RequestBody MovieBase movie) {
		return movieService.viewmovies(movie);
	}
		
	@PostMapping("/MovieByStatus")
	public Object movieByStatus(@RequestBody MovieBase movie) {
		return movieService.movieByStatus(movie);
	}

	@PostMapping("/MovieByCategories")
	public Object movieByCategories (@RequestBody MovieBase movie) {
		return movieService.movieByCategories(movie);
	}
	
	@PostMapping("/MovieByLanguage")
	public Object movieByLanguage (@RequestBody MovieBase movie) {
		return movieService.movieByCategories(movie);
	}
	
	@PostMapping("/UpdateMovie")
	public Object UpdateMovie(@RequestBody MovieBase movie) {
		return movieService.updateMovies(movie);
	}
	
	@PostMapping("/deleteselected")
	public Object deleteselected(@RequestBody String[] data) {	
		return movieService.deleteselected(data);
	}

}
