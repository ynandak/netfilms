package xyz.yogesh.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.yogesh.app.entity.Review;
import xyz.yogesh.app.entity.User;
import xyz.yogesh.app.entity.VideoRelease;
import xyz.yogesh.app.exception.EntityAlreadyExistException;
import xyz.yogesh.app.exception.EntityNotFoundException;
import xyz.yogesh.app.repository.ReviewRepository;
import xyz.yogesh.app.repository.UserRepository;
import xyz.yogesh.app.repository.VideoReleaseRepository;

@Service
public class ReviewService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private VideoReleaseRepository vidRepository;

	public List<Review> findAll(String vidID) {
		return reviewRepository.findAll(vidID);
	}

	@Transactional
	public Review create(String vidID, String userID, Review review) {
		User user = userRepository.findOne(userID);
		if (user == null) {
			throw new EntityNotFoundException("User not found");
		}
		VideoRelease vid = vidRepository.findOne(vidID);
		if (vid == null) {
			throw new EntityNotFoundException("Title not found");
		}
		if (reviewRepository.hasTitleUser(vidID, userID)) {
			throw new EntityAlreadyExistException("Review already exists for user/title combination");
		}
		review.setTitle(vid);
		review.setUser(user);
		return reviewRepository.create(review);
	}
}