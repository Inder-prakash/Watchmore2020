package com.watchmoreonline.services;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class Responses {

	public JSONObject setMsg(Object msg) {
		JSONObject j = new JSONObject();
		j.put("msg", msg);
		return j;
	}
}
