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
	
//	@GetMapping("/logout")
//	public JSONArray logout(HttpServletRequest reqeust) throws ParseException {
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();
//		HttpSession session = reqeust.getSession();
//		session.removeAttribute("Name");
//		session.removeAttribute("Role");
//		if(session.getAttribute("Name") == null)
//		{	
//			json.put("msg", "Success");
//			jrr.add(json);
//		}
//		else {
//			json.put("msg", "Failure");
//			jrr.add(json);
//		}	
//		return jrr;
//	}
//	
	
//	@GetMapping("/userauthorization")
//	public JSONArray userauthorization (HttpServletRequest reqeust) throws ParseException {
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();
//		HttpSession session = reqeust.getSession();
//		System.out.println("Your Session in auth name " +session.getAttribute("Name"));
//		if(session.getAttribute("Name") == null)
//		{	
//			System.out.println("Paasssed");
//			json.put("msg", "Success");
//			jrr.add(json);
//		}
//		else {
//			System.out.println("Session Failed");
//			json.put("msg", "Failure");
//			jrr.add(json);
//		}	
//		return jrr;
//	}
	
//	@GetMapping("/adminauthorization")
//	public JSONArray adminauthorization (HttpServletRequest reqeust) throws ParseException {
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();
//		HttpSession session = reqeust.getSession();
//		if(session.getAttribute("Name") != null || session.getAttribute("Role") == "Admin")
//		{	
//			json.put("msg", "Success");
//			jrr.add(json);
//		}
//		else {
//			json.put("msg", "Failure");
//			jrr.add(json);
//		}	
//		return jrr;
//	}
	
  }
	













//	
//	@PostMapping("/authorization")
//	public JSONArray authorization(@RequestBody String data) throws ParseException {
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();
//			JSONParser jp = new JSONParser();		
//			JSONObject joObject = (JSONObject)jp.parse(data);
//			User u = new User();	
//			String e = joObject.get("email").toString().substring(1, joObject.get("email").toString().length()-1);
//			String t = joObject.get("token").toString().substring(1, joObject.get("token").toString().length()-1);
//			u.setEmail(e);
//			u = udao.find(u);
//			if( u != null && u.getToken().equals(t) )
//			{	
//				json.put("msg", "Success");
//				json.put("role", u.getRole());
//				jrr.add(json);
//			}
//			else {
//				json.put("msg", "Failure");
//				jrr.add(json);
//			}
//		return jrr;
//	}
//	
//	@GetMapping("/jwt")
//	public String jwtsign(String u , String r , String e) throws Exception {
//		JSONObject json = new JSONObject();	
//		JSONArray jrr = new JSONArray();	
//		String header = "{'alg':'HS256'}";
//		String claims = "{'user':'"+u+"'},{'role':'"+r+"'},{'email':'"+e+"'}";
//		String encodedHeader = base64URLEncode( header.getBytes("UTF-8") );
//		String encodedClaims = base64URLEncode( claims.getBytes("UTF-8") );
//		String concatenated = encodedHeader + '.' + encodedClaims;
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
//		String secretkey = key.toString();
//		byte[] signature = hmacSha256(secretkey,concatenated);
//		String jws = concatenated + '.' + base64URLEncode( signature );
//		return jws;		
//	}
//
//	public static byte[] hmacSha256(String key, String data) throws Exception {	
//		  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//		  SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
//		  sha256_HMAC.init(secret_key);
//		  return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8"))).getBytes();
//	}
//	
//	public String base64URLEncode(byte[] data) {
//        return Encoders.BASE64URL.encode(data);
//    }
//}
