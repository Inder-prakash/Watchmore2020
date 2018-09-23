package com.watchmoreonline.tvseries;

import java.util.List;

public interface SerialDao {
	public void insert(Serial s);
	public void update(Serial s);
	public void delete(Serial s);
	public Serial find(String id);
	public List<Serial> findAll();
}
