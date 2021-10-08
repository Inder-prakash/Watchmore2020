package com.watchmoreonline.moviebase;

import java.util.List;

public interface MovieBaseDao {
	public void insert(MovieBase m);
	public void update(MovieBase m);
	public void delete(MovieBase m);
	public MovieBase find(String id);
	public List<MovieBase> movieByStatus(MovieBase m);
	public List<MovieBase> findAll(MovieBase m);
	public List<MovieBase> movieByCategories(MovieBase m);
	public List<MovieBase> movieByLanguage(MovieBase m);
}
