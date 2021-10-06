package com.watchmoreonline.serials;

import java.util.List;

public interface SerialDao {
	public void insert(Serial m);
	public void update(Serial m);
	public void delete(Serial m);
	public Serial find(String id);
	public List<Serial> findAll(Integer page);
	public List<Serial> getTvByStatus(String status,Integer page);

}
