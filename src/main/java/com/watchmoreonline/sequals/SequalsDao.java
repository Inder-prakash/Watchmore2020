package com.watchmoreonline.sequals;

import java.util.List;

public interface SequalsDao {
	public void insert(Sequals m);
	public void update(Sequals m);
	public void delete(Sequals m);
	public Sequals find(String id);
	public Sequals findByBaseId(String id);
	public List<Sequals> findAll();
}
