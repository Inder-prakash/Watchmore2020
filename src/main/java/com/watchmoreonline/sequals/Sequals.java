package com.watchmoreonline.sequals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequals")
public class Sequals {
	
	@Id
	private String id;
	private String baseid;
	private String [] movieid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBaseid() {
		return baseid;
	}
	public void setBaseid(String baseid) {
		this.baseid = baseid;
	}
	
	public String [] getMovieid() {
		return movieid;
	}
	public void setMovieid(String []movieid) {
		this.movieid = movieid;
	}
	public Sequals() {
		super();
	}
	
	
}
