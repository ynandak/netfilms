package xyz.yogesh.app.repository;

import java.util.List;
import java.util.Map;

import xyz.yogesh.app.entity.VideoRelease;

public interface VideoReleaseRepository {

	public List<VideoRelease> findAll(Map<String, String> params);

	public VideoRelease findOne(String vidId);
	
	public VideoRelease findByImdbID(String vidId);

	public VideoRelease create(VideoRelease vid);

	public VideoRelease update(VideoRelease vid);

	public void delete(VideoRelease existing);
}
