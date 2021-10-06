package com.watchmoreonline.moviebase;

import java.util.List;

public interface MovieBaseDao {
	public void insert(MovieBase m);
	public void update(MovieBase m);
	public void delete(MovieBase m);
	public MovieBase find(String id);
	public List<MovieBase> movieByStatus(String status,Integer page);
	public List<MovieBase> findAll(Integer page);
	public List<MovieBase> movieByCategories(String genere, String status,Integer page);
	public List<MovieBase> movieByLanguage(String lang, String status,Integer page);
}
