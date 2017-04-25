package com.project.test.service;

import java.util.List;

import com.project.test.model.User;

public interface UserService {
	public void create(User user);
	public void update(User user);
	public User edit (Long userId);
	public User findByUsername(String un);
	public void delete (Long userId);
	public User findUser(Long userId);
	public List<User> getAll();

}
