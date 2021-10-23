package com.watchmoreonline.moviebase;

import java.util.List;

import com.watchmoreonline.collection.MovieCollection;

public interface MovieBaseDao {
	public void insert(MovieBase m);
	public void update(MovieBase m);
	public void delete(MovieBase m);
	public MovieBase find(String id);
	public List<MovieBase> movieByStatus(MovieBase m);
	public List<MovieBase> findAll(MovieBase m);
	public List<MovieBase> movieByCategories(MovieBase m);
	public List<MovieBase> movieByLanguage(MovieBase m);
	public List<MovieBase> search(Integer page,String text);
	public List<MovieBase> search2(Integer page,String text);
}
