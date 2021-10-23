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
import com.watchmoreonline.moviebase.MovieBase;
import com.watchmoreonline.moviebase.MovieBaseDao;
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.sequals.SequalsDao;
import com.watchmoreonline.services.SequalsService;

@org.springframework.web.bind.annotation.RestController
public class SequalsController {


	@Autowired
	SequalsService sequalsService;
	
	@Autowired
	SequalsDao sdao;

	@Autowired
	MovieBaseDao mov;
	
	@PostMapping("/newsequals")
	public Object newsequals(@RequestBody Sequals sequals) {
		System.out.println(sequals);
		return sequalsService.newsequals(sequals);
	}
	
	@PostMapping("/getsequals")
	public Object getsequals(@RequestBody Sequals sequals) {
		return sequalsService.getsequals(sequals);
	}
	
	@PostMapping("/udatesequal")
	public Object udatesequal(@RequestBody Sequals sequals) {
		return sequalsService.udatesequal(sequals);
	}

}
