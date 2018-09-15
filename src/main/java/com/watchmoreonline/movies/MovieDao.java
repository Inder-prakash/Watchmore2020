package com.watchmoreonline.movies;

import java.util.List;

public interface MovieDao {
	public void insert(Movie m);
	public void update(Movie m);
	public void delete(Movie m);
	public Movie find(String id);
	public List<Movie> findAll();
}
