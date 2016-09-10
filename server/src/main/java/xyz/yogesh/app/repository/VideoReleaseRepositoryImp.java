package xyz.yogesh.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import xyz.yogesh.app.entity.VideoRelease;

@Repository
public class VideoReleaseRepositoryImp implements VideoReleaseRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<VideoRelease> findAll() {
		TypedQuery<VideoRelease> query = em.createNamedQuery("VideoRelease.findAll", VideoRelease.class);
		return query.getResultList();
	}

	@Override
	public VideoRelease findOne(String vidId) {
		return em.find(VideoRelease.class, vidId);
	}

	@Override
	public VideoRelease findByImdbID(String imdbID) {
		TypedQuery<VideoRelease> query = em.createNamedQuery("VideoRelease.findByImdbID", VideoRelease.class);
		query.setParameter("pImdbID", imdbID);
		List<VideoRelease> vids = query.getResultList();
		if (vids.size() == 1) {
			return vids.get(0);
		} else {
			return null;
		}
	}

	@Override
	public VideoRelease create(VideoRelease vid) {
		em.persist(vid);
		return vid;
	}

	@Override
	public VideoRelease update(VideoRelease vid) {
		return em.merge(vid);
	}

	@Override
	public void delete(VideoRelease existing) {
		em.remove(existing);
	}
}