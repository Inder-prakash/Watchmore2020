package com.watchmoreonline;
import org.apache.commons.codec.binary.Hex;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.watchmoreonline.users.User;
import com.watchmoreonline.users.UserDao;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
	public JSONArray login(@RequestBody String data) {
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
				if(u.getStatus().equals("true")) {
					u.setToken(jwtsign(u.getUsername(),u.getEmail(),u.getRole()));
					udao.update(u);
				}
				json.put("msg", "Success");
				json.put("username", u.getUsername());
				json.put("email", u.getEmail());
				json.put("status", u.getStatus());
				json.put("token", u.getToken());
				json.put("role", u.getRole());
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
	
	@PostMapping("/authorization")
	public JSONArray authorization(@RequestBody String data) {
		JSONObject json = new JSONObject();	
		JSONArray jrr = new JSONArray();
		try
		{
			JSONParser jp = new JSONParser();		
			JSONObject joObject = (JSONObject)jp.parse(data);
			User u = new User();	
			String e = joObject.get("email").toString().substring(1, joObject.get("email").toString().length()-1);
			String t = joObject.get("token").toString().substring(1, joObject.get("token").toString().length()-1);
			u.setEmail(e);
			u = udao.find(u);
			if( u != null && u.getToken().equals(t) )
			{	
				json.put("msg", "Success");
				json.put("role", u.getRole());
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
	
	@GetMapping("/jwt")
	public String jwtsign(String u , String r , String e) throws Exception {
		JSONObject json = new JSONObject();	
		JSONArray jrr = new JSONArray();	
		String header = "{'alg':'HS256'}";
		String claims = "{'user':'"+u+"'},{'role':'"+r+"'},{'email':'"+e+"'}";
		String encodedHeader = base64URLEncode( header.getBytes("UTF-8") );
		String encodedClaims = base64URLEncode( claims.getBytes("UTF-8") );
		String concatenated = encodedHeader + '.' + encodedClaims;
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
		String secretkey = key.toString();
		byte[] signature = hmacSha256(secretkey,concatenated);
		String jws = concatenated + '.' + base64URLEncode( signature );
		return jws;		
	}

	public static byte[] hmacSha256(String key, String data) throws Exception {	
		  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		  SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		  sha256_HMAC.init(secret_key);
		  return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8"))).getBytes();
	}
	
	public String base64URLEncode(byte[] data) {
        return Encoders.BASE64URL.encode(data);
    }
}
