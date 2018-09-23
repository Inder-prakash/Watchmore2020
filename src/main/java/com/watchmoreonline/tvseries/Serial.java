package com.watchmoreonline.tvseries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tvseries")
public class Serial {
	@Id
	private String id;
	private String name;
	private String image;
	private String language;
	private String status;
	private List<String> episodes = new ArrayList<String>();
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
	public List<String> getEpisodes() {
		return episodes;
	}
	public void setEpisodes(List<String> episodes) {
		this.episodes = episodes;
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
