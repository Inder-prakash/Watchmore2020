package com.watchmoreonline.moviebase;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "moviesbase")
public class MovieBase {
	@Id
	private String id;
	private String name;
	private String image;
	private String Language;
	private String Status;
	private String Size;
	private String Genere;
	private String Discription;
	private String Link;

	

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



	public String getLanguage() {
		return Language;
	}



	public void setLanguage(String language) {
		Language = language;
	}



	public String getStatus() {
		return Status;
	}



	public void setStatus(String status) {
		Status = status;
	}



	public String getSize() {
		return Size;
	}



	public void setSize(String size) {
		Size = size;
	}



	public String getGenere() {
		return Genere;
	}



	public void setGenere(String genere) {
		Genere = genere;
	}



	public String getDiscription() {
		return Discription;
	}



	public void setDiscription(String discription) {
		Discription = discription;
	}



	public String getLink() {
		return Link;
	}



	public void setLink(String link) {
		Link = link;
	}



	public MovieBase() {
		super();
	}
	
	
}
