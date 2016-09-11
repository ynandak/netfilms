package xyz.yogesh.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.yogesh.app.entity.VideoRelease;
import xyz.yogesh.app.entity.raw.RawVideoRelease;
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
	public VideoRelease create(RawVideoRelease rawVid) {
		VideoRelease existing = repository.findByImdbID(rawVid.getImdbID());
		if (existing != null) {
			throw new EntityAlreadyExistException("Video Release already exists with this IMDB ID");
		}
		return repository.create(rawVid.flatten());
	}

	@Transactional
	@Override
	public VideoRelease update(String vidId, RawVideoRelease rawVid) {
		VideoRelease existing = repository.findOne(vidId);
		if (existing == null) {
			throw new EntityNotFoundException("Video Release not found");
		}
		rawVid.setID(vidId);
		return repository.update(rawVid.flatten());
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