package xyz.yogesh.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.yogesh.app.entity.VideoRelease;
import xyz.yogesh.app.exception.EntityAlreadyExistException;
import xyz.yogesh.app.exception.EntityNotFoundException;
import xyz.yogesh.app.repository.VideoReleaseRepository;

@Service
public class VideoReleaseServiceImp implements VideoReleaseService {

	@Autowired
	private VideoReleaseRepository repository;

	@Override
	public List<VideoRelease> findAll() {
		return repository.findAll();
	}

	@Override
	public VideoRelease findOne(String vidId) {
		VideoRelease vid = repository.findOne(vidId);
		if (vid == null) {
			throw new EntityNotFoundException("Video Release not found");
		}
		return vid;
	}

	@Transactional
	@Override
	public VideoRelease create(VideoRelease vid) {
		VideoRelease existing = repository.findByImdbID(vid.getImdbID());
		if (existing != null) {
			throw new EntityAlreadyExistException("Video Release already exists with this IMDB ID");
		}
		return repository.create(vid);
	}

	@Transactional
	@Override
	public VideoRelease update(String vidId, VideoRelease vid) {
		VideoRelease existing = repository.findOne(vidId);
		if (existing == null) {
			throw new EntityNotFoundException("Video Release not found");
		}
		vid.setID(vidId);
		System.err.println(vid.toString());
		return repository.update(vid);
	}

	@Transactional
	@Override
	public void remove(String vidId) {
		VideoRelease existing = repository.findOne(vidId);
		if (existing == null) {
			throw new EntityNotFoundException("Video Release not found");
		}
		repository.delete(existing);
	}
}