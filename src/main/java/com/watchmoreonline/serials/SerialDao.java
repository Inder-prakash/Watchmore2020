package com.watchmoreonline.serials;

import java.util.List;

public interface SerialDao {
	public void insert(Serial m);
	public void update(Serial m);
	public void delete(Serial m);
	public Serial find(String id);
	public List<Serial> findAll(Serial m);
	public List<Serial> getTvByStatus(Serial m);

}
