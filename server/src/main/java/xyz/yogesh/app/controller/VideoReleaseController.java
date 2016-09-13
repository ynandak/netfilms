package xyz.yogesh.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.yogesh.app.entity.VideoRelease;
import xyz.yogesh.app.entity.raw.RawVideoRelease;
import xyz.yogesh.app.service.VideoReleaseService;

@RestController
@RequestMapping(value = "videoreleases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VideoReleaseController {

	@Autowired
	private VideoReleaseService service;

/*	@RequestMapping(method = RequestMethod.GET)
	public List<VideoRelease> findAll() {
		return service.findAll();
	}*/
	
	@RequestMapping(method = RequestMethod.GET)
	public List<VideoRelease> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public VideoRelease findOne(@PathVariable("id") String vidId) {
		return service.findOne(vidId);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public VideoRelease create(@RequestBody RawVideoRelease vid) {
		return service.create(vid);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public VideoRelease update(@PathVariable("id") String vidId, @RequestBody RawVideoRelease vid) {
		return service.update(vidId, vid);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable("id") String vidId) {
		service.remove(vidId);
	}
}