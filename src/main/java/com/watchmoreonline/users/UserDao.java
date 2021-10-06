package com.watchmoreonline.users;

public interface UserDao {
	public void insert(User User);
	public void delete(User User);
	public void update(User User);
	public User checkByEmail(String email);
	public User findByPassword(String email, String password);
}
