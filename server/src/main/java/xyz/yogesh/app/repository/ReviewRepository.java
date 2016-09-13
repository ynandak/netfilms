package xyz.yogesh.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import xyz.yogesh.app.entity.Review;

@Repository
public class ReviewRepository{

	@PersistenceContext
	private EntityManager em;

	public List<Review> findAll(String vidID) {
		TypedQuery<Review> query = em.createNamedQuery("Review.findAll", Review.class);
		query.setParameter("pVidID", vidID);
		return query.getResultList();
	}
	
	public Review create(Review review) {
		em.persist(review);
		return review;
	}

	public boolean hasTitleUser(String vidID, String userID) {
		TypedQuery<Review> query = em.createNamedQuery("Review.findByTitleUser", Review.class);
		query.setParameter("pVidID", vidID);
		query.setParameter("pUserID", userID);
		if(query.getResultList().size()==0) {
			return false;
		}
		return true;
	}
	
	//Implementing on delete cascade for User -> Review
	public boolean cascadeOnDeleteUser(String userID) {
		Query query = em.createNamedQuery("Review.deleteByUser", Review.class);
		query.setParameter("pUserID", userID);
		query.executeUpdate();
		return true;
	}
	
	//Implementing on delete cascade for VideoRelease -> Review
	public boolean cascadeOnDeleteVideoRelease(String vidID) {
		Query query = em.createNamedQuery("Review.deleteByTitle", Review.class);
		query.setParameter("pVidID", vidID);
		query.executeUpdate();
		return true;
	}
}