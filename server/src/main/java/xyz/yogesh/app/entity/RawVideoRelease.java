package xyz.yogesh.app.entity;

import java.util.Arrays;
import java.util.UUID;

public class RawVideoRelease {

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
	public String imdbID;
	public String type;

	public RawVideoRelease() {
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

	public VideoRelease flatten() {
		VideoRelease vid = new VideoRelease();
		vid.setId(id);
		vid.setTitle(title);
		vid.setYear(year);
		vid.setRated(rated);
		vid.setReleased(released);
		vid.setRuntime(runtime);
		vid.setDirector(director);
		vid.setWriter(writer);
		vid.setActors(actors);
		vid.setPlot(plot);
		vid.setLanguage(language);
		vid.setCountry(country);
		vid.setAwards(awards);
		vid.setPoster(poster);
		vid.setMetascore(metascore);
		vid.setImdbRating(imdbRating);
		vid.setImdbVotes(imdbVotes);
		vid.setImdbID(imdbID);
		vid.setType(type);
		vid.setGenre(Arrays.asList(this.genre.split("\\s*,\\s*")));
		return vid;
	}
}