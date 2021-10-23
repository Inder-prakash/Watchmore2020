package com.watchmoreonline.serials;

import java.util.List;

import com.watchmoreonline.moviebase.MovieBase;

public interface SerialDao {
	public void insert(Serial m);
	public void update(Serial m);
	public void delete(Serial m);
	public Serial find(String id);
	public List<Serial> findAll(Serial m);
	public List<Serial> getTvByStatus(Serial m);
	public List<Serial> search(Integer page,String text);
	public List<Serial> search2(Integer page,String text);
	public List<Serial> findAll();
}
