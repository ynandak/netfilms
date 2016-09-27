package xyz.yogesh.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.yogesh.app.entity.Review;
import xyz.yogesh.app.service.ReviewService;

@RestController
@RequestMapping(value = "/videoreleases/{vidID}/reviews", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReviewController {

	@Autowired
	private ReviewService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<Review> findAll(@PathVariable("vidID") String vidID) {
		return service.findAll(vidID);
	}

	//userID from the session would probably be a better implementation
	@RequestMapping(method = RequestMethod.POST, value = "{userID}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Review create(@PathVariable("vidID") String vidID, @PathVariable("userID") String userID, @RequestBody Review review) {
		return service.create(vidID, userID, review);
	}
}