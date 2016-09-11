package xyz.yogesh.app.repository;

import java.util.List;

import xyz.yogesh.app.entity.User;

public interface UserRepository {

	public List<User> findAll();

	public User findOne(String userId);
	
	public User findByEmail(String userId);

	public User create(User user);

	public User update(User user);

	public void delete(User existing);
}
