package com.project.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.test.model.User;
import com.project.test.repositories.UserRepositoryImpl;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepositoryImpl userRepo;

	@Override
	public void create(User user) {
		userRepo.create(user);
		
	}

	@Override
	public void update(User user) {
		userRepo.update(user);		
	}

	@Override
	public User edit(Long userId) {
		return userRepo.edit(userId);
	}

	@Override
	public User findByUsername(String un) {
		return userRepo.findByUsername(un);
	}

	@Override
	public void delete(Long userId) {
		userRepo.delete(userId);		
	}

	@Override
	public User findUser(Long userId) {
		return userRepo.findUser(userId);
	}

	@Override
	public List<User> getAll() {
		return userRepo.getAll();
	}

}
