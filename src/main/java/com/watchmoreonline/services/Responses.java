package com.watchmoreonline.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class Responses {

	public JSONObject setMsg(Object msg) {
		JSONObject j = new JSONObject();
		j.put("msg", msg);
		return j;
	}
	
	public JSONArray setMsg2(Object msg,Integer total) {
		JSONArray jar = new  JSONArray();
		JSONObject j = new JSONObject();
		j.put("msg", msg);
		j.put("total",total);
		jar.add(j);
		return jar;
	}
}
