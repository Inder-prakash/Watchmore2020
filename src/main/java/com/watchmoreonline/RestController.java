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
public class RestController {


	@Autowired
	UserDao udao;
	
	@RequestMapping("/")
    @ResponseBody
    String hello() {
        return "Backend is running.";
    }	

	
	@PostMapping("/signup")
	public String signup(@RequestBody String data) throws ParseException {
		JSONObject json = new JSONObject();
		JSONParser jp = new JSONParser();		
		JSONObject joObject = (JSONObject)jp.parse(data);		
		User u = new User();	
		u.setEmail(joObject.get("email").toString());
		u = udao.find(u);
		if( u == null ) {
			u = new User();
			u.setEmail(joObject.get("email").toString());
			u.setPassword(joObject.get("password").toString());
			u.setUsername(joObject.get("username").toString());
			udao.add(u);
			json.put("msg", "Success");
		}
		else {
			json.put("msg", "Failed");
		}
		return json.toJSONString();
	}
	
	@PostMapping("/login")
	public JSONArray login( @RequestBody String data) {
		JSONObject json = new JSONObject();	
		JSONArray jrr = new JSONArray();
		try
		{
			JSONParser jp = new JSONParser();		
			JSONObject joObject = (JSONObject)jp.parse(data);
			User u = new User();	
			u.setEmail(joObject.get("Email").toString());
			u = udao.find(u);	
			if( u != null && u.getPassword().equals( joObject.get("Password").toString()))
			{			
				json.put("username", u.getUsername());
				json.put("role", u.getRole());
				json.put("status", u.getStatus());
				json.put("msg", "Success");	
				json.put("token",generateJwt(u.getUsername(),u.getRole(),u.getEmail()));
				jrr.add(json);
			}
			else {
				json.put("msg", "Failure");
				jrr.add(json);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			json.put("msg", "Failure");
			jrr.add(json);
		}
		return jrr;
	}
	

	public String generateJwt(String username,String role, String email) {
		Signer signer512 = HMACSigner.newSHA512Signer("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3");
		JWT jwt = new JWT().setIssuer("www.watch-itv.com")
		                   .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
		                   .setSubject("HAHABABABA")
		                   .addClaim("username",username)
		                   .addClaim("role", role)
		                   .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
		return JWT.getEncoder().encode(jwt, signer512);
	}
	
	
	
	@PostMapping("/userauthorization")
	public JSONArray userauthorization (@RequestBody String token) throws ParseException {		
		JSONObject json = new JSONObject();	
		JSONArray jrr = new JSONArray();			
		Verifier verifier = HMACVerifier.newVerifier("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3");
		try {
			JWT jwt2 = JWT.getDecoder().decode(token, verifier);
			json.put("msg", "Success");
			jrr.add(json);
		}
		catch (Exception e) {
			json.put("msg", "Failure");
			jrr.add(json);
		}	
		return jrr;
	}
	
	
	@PostMapping("/adminauthorization")
	public JSONArray adminauthorization (@RequestBody String token) throws ParseException {		
		JSONObject json = new JSONObject();	
		JSONArray jrr = new JSONArray();		
		Verifier verifier = HMACVerifier.newVerifier("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3");
		try {
			JWT jwt2 = JWT.getDecoder().decode(token, verifier);
			if(jwt2.getAllClaims().get("role").equals("Admin")) {
				json.put("msg", "Success");
				jrr.add(json);
			}
			else {
				json.put("msg", "Failure");
				jrr.add(json);
			}
		}
		catch (Exception e) {
			json.put("msg", "Failure");
			jrr.add(json);
		}	
		return jrr;
	}
			
  }
	
