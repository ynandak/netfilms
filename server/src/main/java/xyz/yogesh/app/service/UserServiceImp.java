package xyz.yogesh.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.yogesh.app.entity.User;
import xyz.yogesh.app.exception.ActionNotAllowedException;
import xyz.yogesh.app.exception.EntityAlreadyExistException;
import xyz.yogesh.app.exception.EntityNotFoundException;
import xyz.yogesh.app.repository.ReviewRepository;
import xyz.yogesh.app.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findOne(String userId) {
		User user = repository.findOne(userId);
		if (user == null) {
			throw new EntityNotFoundException("User not found");
		}
		return user;
	}

	@Transactional
	@Override
	public User create(User user) {
		if (user.getUserType() != "User") {
			throw new ActionNotAllowedException("A user of this type cannot be created.");
		}
		User existing = repository.findByEmail(user.getEmail());
		if (existing != null) {
			throw new EntityAlreadyExistException("User already exists with this email ID");
		}
		user.setPassword(HashService.hash(user.getPassword()));
		return repository.create(user);
	}

	@Transactional
	@Override
	public User update(String userId, User user) {
		if (user.getUserType() != "User") {
			throw new ActionNotAllowedException("A user of this type cannot be created.");
		}
		User existing = repository.findOne(userId);
		if (existing == null) {
			throw new EntityNotFoundException("User not found");
		}
		user.setPassword(HashService.hash(user.getPassword()));
		user.setId(userId);
		return repository.update(user);
	}

	@Transactional
	@Override
	public void remove(String userId) {
		User existing = repository.findOne(userId);
		if (existing == null) {
			throw new EntityNotFoundException("User not found");
		}
		reviewRepository.cascadeOnDeleteUser(userId);
		repository.delete(existing);
	}

	@Override
	public User login(User u) {
		User user = repository.findByEmail(u.getEmail());
		if (user == null) {
			throw new EntityNotFoundException("User not found");
		}
		else if (!HashService.check(u.getPassword(), user.getPassword())) {
			return null;
		}
		return user;
	}
}