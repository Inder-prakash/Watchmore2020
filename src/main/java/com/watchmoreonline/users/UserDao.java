package com.watchmoreonline.users;

import java.util.List;

public interface UserDao {
	public void insert(User User);
	public void delete(User User);
	public void update(User User);
	public User checkByEmail(String email);
	public User findByPassword(String email, String password);
	public User findById(String id);
	public List<User> findall();
}
