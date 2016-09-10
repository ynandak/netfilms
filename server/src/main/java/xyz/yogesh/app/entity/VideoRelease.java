package xyz.yogesh.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
		@NamedQuery(name = "VideoRelease.findAll", query = "SELECT v from VideoRelease v ORDER BY v.title"),
		@NamedQuery(name = "VideoRelease.findByImdbID", query = "SELECT v from VideoRelease v where v.imdbID=:pImdbID")
})
public class VideoRelease {

	@Id
	private String id;

	public String title;
	public Integer year;
	public String rated;
	public String released;
	public String runtime;
	public String genre;
	public String director;
	public String writer;
	public String actors;
	public String plot;
	public String language;
	public String country;
	public String awards;
	public String poster;
	public Integer metascore;
	public Double imdbRating;
	public Integer imdbVotes;
	
	@Column(unique=true)
	public String imdbID;
	
	public String type;

	public VideoRelease() {
		id = UUID.randomUUID().toString();
	}
	
	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	
	public String getID() {
		return id;
	}

	public void setID(String imdbID) {
		this.id = imdbID;
	}

	@Override
	public String toString() {
		return "VideoRelease [id=" + id + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
				+ released + ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer="
				+ writer + ", actors=" + actors + ", plot=" + plot + ", language=" + language + ", country=" + country
				+ ", awards=" + awards + ", poster=" + poster + ", metascore=" + metascore + ", imdbRating="
				+ imdbRating + ", imdbVotes=" + imdbVotes + ", imdbID=" + imdbID + ", type=" + type + "]";
	}
	
}