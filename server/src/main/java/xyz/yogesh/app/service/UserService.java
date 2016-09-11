package xyz.yogesh.app.service;

import java.util.List;

import xyz.yogesh.app.entity.User;

public interface UserService {

	public List<User> findAll();

	public User findOne(String userId);

	public User create(User user);

	public User update(String vidId, User user);

	public void remove(String userId);
}