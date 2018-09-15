package com.watchmoreonline.movies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movie {
	@Id
	private String id;
	private String name;
	private String image;
	private String directlink480p;
	private String directlink720p;
	private String directlink1080p;
	private String directlink4k;
	private String play480p;
	private String play720p;
	private String play1080p;
	private String play4k;
	private String download480p;
	private String download720p;
	private String download1080p;
	private String download4k;
	private String size480p;
	private String size720p;
	private String size1080p;
	private String size4k;

	
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


	public String getDirectlink480p() {
		return directlink480p;
	}


	public void setDirectlink480p(String directlink480p) {
		this.directlink480p = directlink480p;
	}


	public String getDirectlink720p() {
		return directlink720p;
	}


	public void setDirectlink720p(String directlink720p) {
		this.directlink720p = directlink720p;
	}


	public String getDirectlink1080p() {
		return directlink1080p;
	}


	public void setDirectlink1080p(String directlink1080p) {
		this.directlink1080p = directlink1080p;
	}


	public String getDirectlink4k() {
		return directlink4k;
	}


	public void setDirectlink4k(String directlink4k) {
		this.directlink4k = directlink4k;
	}

	public String getPlay480p() {
		return play480p;
	}


	public void setPlay480p(String play480p) {
		this.play480p = play480p;
	}


	public String getPlay720p() {
		return play720p;
	}


	public void setPlay720p(String play720p) {
		this.play720p = play720p;
	}


	public String getPlay1080p() {
		return play1080p;
	}


	public void setPlay1080p(String play1080p) {
		this.play1080p = play1080p;
	}


	public String getPlay4k() {
		return play4k;
	}


	public void setPlay4k(String play4k) {
		this.play4k = play4k;
	}


	public String getDownload480p() {
		return download480p;
	}


	public void setDownload480p(String download480p) {
		this.download480p = download480p;
	}


	public String getDownload720p() {
		return download720p;
	}


	public void setDownload720p(String download720p) {
		this.download720p = download720p;
	}


	public String getDownload1080p() {
		return download1080p;
	}


	public void setDownload1080p(String download1080p) {
		this.download1080p = download1080p;
	}


	public String getDownload4k() {
		return download4k;
	}


	public void setDownload4k(String download4k) {
		this.download4k = download4k;
	}

	

	public String getSize480p() {
		return size480p;
	}


	public void setSize480p(String size480p) {
		this.size480p = size480p;
	}

	

	public String getSize720p() {
		return size720p;
	}


	public void setSize720p(String size720p) {
		this.size720p = size720p;
	}


	public String getSize1080p() {
		return size1080p;
	}


	public void setSize1080p(String size1080p) {
		this.size1080p = size1080p;
	}


	public String getSize4k() {
		return size4k;
	}


	public void setSize4k(String size4k) {
		this.size4k = size4k;
	}


	public Movie() {
		super();
	}
	
	
}
