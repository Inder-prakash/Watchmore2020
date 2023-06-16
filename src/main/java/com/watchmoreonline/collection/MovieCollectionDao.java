package com.watchmoreonline.collection;

import java.util.List;

public interface MovieCollectionDao {
	public void insert(MovieCollection m);
	public void update(MovieCollection m);
	public void delete(MovieCollection m);
	public MovieCollection find(String id);
	public List<MovieCollection> findAll();
}
