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
import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.moviebase.MovieBaseDao;
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.sequals.SequalsDao;
import com.watchmoreonline.serials.Serial;
import com.watchmoreonline.services.MoviePackService;

@org.springframework.web.bind.annotation.RestController
public class MoviepackController {
	
	@Autowired
	public MovieCollectionDao mov;
	
	@Autowired
	public MoviePackService moviePackService;
	
	@Autowired
	SequalsDao sdao;
	@Autowired
	MovieBaseDao move;
	
	@GetMapping("/w")
	public void w() {
		moviePackService.find();
	}
	
	@PostMapping("/newcollection")
	public Object newcollection(@RequestBody MovieCollection movieCollection) {
		return moviePackService.newcollection(movieCollection);
	}
	
	@GetMapping("/viewmoveiepack")
	public Object viewmoveiepack() {	
		return moviePackService.viewmoveiepack();
	}
	
	@PostMapping("/getmoviesByMoviePack")
	public Object getmoviesByMoviePack(@RequestBody MovieCollection movieCollection) {	
		return moviePackService.getmoviesByMoviePack(movieCollection);
	}
	
	@PostMapping("/updatemoviepackname")
	public Object updatemoviepackname(@RequestBody MovieCollection movieCollection) {	
		return moviePackService.updatemoviepackname(movieCollection);
	}
	
	@PostMapping("/deletepack")
	public Object deletepack(@RequestBody MovieCollection movieCollection) {	
		return moviePackService.deletepack(movieCollection);
	}
	
	
}
