package com.watchmoreonline;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.watchmoreonline.services.UserService;
import com.watchmoreonline.users.User;
import com.watchmoreonline.users.UserDao;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@org.springframework.web.bind.annotation.RestController
public class UserController {


	@Autowired
	UserDao udao;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
    @ResponseBody
    String hello() {
		return "Backend is running.";
    }	
	
	@PostMapping("/signup")
	public Object signup(@RequestBody User user) {
		return userService.signup(user);
	}
	
	@GetMapping("/allUsers")
	public Object allUsers() {
		return userService.allusers();
	}
	
	@PostMapping("/deleteUser")
	public Object deleteUser(@RequestBody User u) {
		return userService.deleteUser(u);
	}
	
	@PostMapping("/updateUser")
	public Object updateUser(@RequestBody User u) {
		return userService.updateUser(u);
	}
	
//	@PostMapping("/signup")
//	public String signup(@RequestBody String data) throws ParseException {
//		JSONObject json = new JSONObject();
//		JSONParser jp = new JSONParser();		
//		JSONObject joObject = (JSONObject)jp.parse(data);		
//		User u = new User();	
//		u.setEmail(joObject.get("email").toString());
//		u = udao.find(u);
//		if( u == null ) {
//			u = new User();
//			u.setEmail(joObject.get("email").toString());
//			u.setPassword(joObject.get("password").toString());
//			u.setUsername(joObject.get("username").toString());
//			udao.add(u);
//			json.put("msg", "Success");
//		}
//		else {
//			json.put("msg", "Failed");
//		}
//		return json.toJSONString();
//	}
	
	@PostMapping("/login")
	public Object login( @RequestBody User user) {
		return userService.login(user);
	}
	
//	@PostMapping("/login")
//	public JSONArray login( @RequestBody String data) {
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();
//		try
//		{
//			JSONParser jp = new JSONParser();		
//			JSONObject joObject = (JSONObject)jp.parse(data);
//			User u = new User();	
//			u.setEmail(joObject.get("Email").toString());
//			u = udao.find(u);	
//			if( u != null && u.getPassword().equals( joObject.get("Password").toString()))
//			{			
//				json.put("username", u.getUsername());
//				json.put("role", u.getRole());
//				json.put("status", u.getStatus());
//				json.put("msg", "Success");	
//				json.put("token",generateJwt(u.getUsername(),u.getRole(),u.getEmail()));
//				jrr.add(json);
//			}
//			else {
//				json.put("msg", "Failure");
//				jrr.add(json);
//			}
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//			json.put("msg", "Failure");
//			jrr.add(json);
//		}
//		return jrr;
//	}
	

//	@PostMapping("/userauthorization")
//	public JSONArray userauthorization (@RequestBody String token) throws ParseException {		
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();			
//		Verifier verifier = HMACVerifier.newVerifier("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3");
//		try {
//			JWT jwt2 = JWT.getDecoder().decode(token, verifier);
//			json.put("msg", "Success");
//			jrr.add(json);
//		}
//		catch (Exception e) {
//			json.put("msg", "Failure");
//			jrr.add(json);
//		}	
//		return jrr;
//	}
	
	
//	@PostMapping("/adminauthorization")
//	public JSONArray adminauthorization (@RequestBody String token) throws ParseException {		
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();		
//		Verifier verifier = HMACVerifier.newVerifier("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3");
//		try {
//			JWT jwt2 = JWT.getDecoder().decode(token, verifier);
//			if(jwt2.getAllClaims().get("role").equals("Admin")) {
//				json.put("msg", "Success");
//				jrr.add(json);
//			}
//			else {
//				json.put("msg", "Failure");
//				jrr.add(json);
//			}
//		}
//		catch (Exception e) {
//			json.put("msg", "Failure");
//			jrr.add(json);
//		}	
//		return jrr;
//	}
			
  }
	
