package xyz.yogesh.app.repository;

import java.util.List;

import xyz.yogesh.app.entity.VideoRelease;

public interface VideoReleaseRepository {

	public List<VideoRelease> findAll();

	public VideoRelease findOne(String vidId);
	
	public VideoRelease findByImdbID(String vidId);

	public VideoRelease create(VideoRelease vid);

	public VideoRelease update(VideoRelease vid);

	public void delete(VideoRelease existing);
}
