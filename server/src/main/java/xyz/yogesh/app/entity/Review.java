package xyz.yogesh.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table
@NamedQueries({
		@NamedQuery(name = "Review.findAll", query = "SELECT r from Review r where r.title.id=:pVidID"),
		@NamedQuery(name = "Review.findByTitleUser", query = "SELECT r from Review r where r.title.id=:pVidID and r.user.id=:pUserID"),
		@NamedQuery(name = "Review.deleteByTitle", query = "delete from Review r where r.title.id=:pVidID"),
		@NamedQuery(name = "Review.deleteByUser", query = "delete from Review r where r.user.id=:pUserID")
})
public class Review {

	@Id
	private String id;

	@OneToOne
	private User user;
	
	@OneToOne
	private VideoRelease title;
	
	@Min(value = 1)
	@Max(value = 5)
	private Integer rating;
	
	@Column(length=2000)
	private String review;

	public Review() {
		id = UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", user=" + user + ", title=" + title + ", rating=" + rating + ", review=" + review
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VideoRelease getTitle() {
		return title;
	}

	public void setTitle(VideoRelease title) {
		this.title = title;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
}