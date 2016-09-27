package xyz.yogesh.app.service;

import java.util.List;
import java.util.Map;

import xyz.yogesh.app.entity.VideoRelease;
import xyz.yogesh.app.entity.raw.RawVideoRelease;

public interface VideoReleaseService {

	public List<VideoRelease> findAll(Map<String, String> params);

	public VideoRelease findOne(String vidId);

	public VideoRelease create(RawVideoRelease vid);

	public VideoRelease update(String vidId, RawVideoRelease vid);

	public void remove(String vidId);
}