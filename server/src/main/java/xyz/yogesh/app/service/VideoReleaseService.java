package xyz.yogesh.app.service;

import java.util.List;

import xyz.yogesh.app.entity.VideoRelease;

public interface VideoReleaseService {

	public List<VideoRelease> findAll();

	public VideoRelease findOne(String vidId);

	public VideoRelease create(VideoRelease vid);

	public VideoRelease update(String vidId, VideoRelease vid);

	public void remove(String vidId);
}