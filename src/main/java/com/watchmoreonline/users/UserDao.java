package com.watchmoreonline.users;

public interface UserDao {
	public User find(User User);
	public void add(User User);
	public void delet(User User);
	public void update(User User);
}
