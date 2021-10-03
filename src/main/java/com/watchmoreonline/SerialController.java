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
import com.watchmoreonline.sequals.Sequals;
import com.watchmoreonline.serials.Serial;
import com.watchmoreonline.serials.SerialDao;
import com.watchmoreonline.services.SerialService;

@org.springframework.web.bind.annotation.RestController
public class SerialController {

	@Autowired
	SerialDao sd;
	
	@Autowired
	SerialService serialService;
	
	@PostMapping("/addserial")
	public Object addserial(@RequestBody Serial serial ) {
		return serialService.addserial(serial);
	}
	
	@GetMapping("/viewtv")
	public Object viewtv() {
		return serialService.viewtv();
	}
	
	@PostMapping("/gettv")
	public Object gettv(@RequestBody Serial serial) {
		return serialService.gettv(serial);
	}
	
	@GetMapping("/getTvByStatus")
	public Object getTvByStatus(@RequestBody Serial serial) {
		return serialService.publictv();
	}
	
	@PostMapping("/updatepisodes")
	public Object updatepisodes(@RequestBody Serial serial ) {
		return serialService.updatepisodes(serial);
	}
	
	
	@PostMapping("/updatetv")
	public Object updatetv(@RequestBody Serial serial ) {
		return serialService.updatetv(serial);
	}
	
	@PostMapping("/deletetv")
	public Object deletetv(@RequestBody Serial serial ) {
		return serialService.deletetv(serial);
	}
}
