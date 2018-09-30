package com.watchmoreonline.serials;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "serials")
public class Serial {

	@Id
	private String id;
	private String name;
	private String image;
	private String status;
	private String language;
	private List<String> ename = new ArrayList<String>();
	private List<String> elink = new ArrayList<String>();
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public List<String> getEname() {
		return ename;
	}



	public void setEname(List<String> ename) {
		this.ename = ename;
	}



	public List<String> getElink() {
		return elink;
	}



	public void setElink(List<String> elink) {
		this.elink = elink;
	}


	

	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	


	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public Serial() {
		super();
	}
	
	
}
