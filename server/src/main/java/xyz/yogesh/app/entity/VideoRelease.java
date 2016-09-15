package xyz.yogesh.app.entity;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
		//@NamedQuery(name = "VideoRelease.findAll", query = "SELECT v from VideoRelease v ORDER BY v.title"),
		@NamedQuery(name = "VideoRelease.findByImdbID", query = "SELECT v from VideoRelease v where v.imdbID=:pImdbID")
})
public class VideoRelease {

	@Id
	private String id;

	private String title;
	private Integer year;
	private String rated;
	private Date released;
	private String runtime;
	
	//Using @ElementCollection since it isn't an entity
	@ElementCollection
	private List<String> genre;
	
	private String director;
	private String writer;
	private String actors;
	
	@Column(length=2000)
	private String plot;
	private String language;
	private String country;
	private String awards;
	private String poster;
	private Integer metascore;
	private Double imdbRating;
	private Integer imdbVotes;
	
	@Column(unique=true)
	private String imdbID;
	
	private String type;
	private Double userAvgRating;
	private Integer numVotes;

	public VideoRelease() {
		//id = UUID.randomUUID().toString();
		numVotes = 0;
	}
	
	//Maintaining average rating for titles, assuming that reviews/ratings cannot be removed.
	//Not setting userAvgRating = 0 in constructor so that movie with no ratings does not have 0 rating
	public void addReview(Review review) {
		if(review.getRating() != null) {
			if(numVotes == 0) {
				userAvgRating = 0.0;
			}
			userAvgRating = ((userAvgRating * numVotes) + review.getRating())/++numVotes;
		}
	}
	
	public static String getCustom(Map<String, String> params) {
		Boolean whereFlag = false;
		
		//Iterating through parameter list and building query
		StringBuilder query = new StringBuilder("SELECT v from VideoRelease v");
		for (Map.Entry<String, String> param : params.entrySet()) {
			System.err.println("Map value: " + param.getKey() + " = " + param.getValue());	//PRINT STATEMENT
		    if(fieldExists(param.getKey())) {
		    	if(whereFlag == false) {
		    		whereFlag = true;
		    		query.append(" where");
		    	}
		    	else {
		    		query.append(" and");
		    	}
	    		query.append(" v." + param.getKey() + "=\"" + param.getValue() + "\"");
		    }
		}
		
		//Setting order by statement, default is sort by title
		StringBuilder sortStatement = new StringBuilder(" order by v.");
		if(params.containsKey("sort")) {
			if(!fieldExists(params.get("sort"))) {
				sortStatement.append("title");
			}
			else {
				if((params.containsKey("sortOrder")) && (params.get("sortOrder").equalsIgnoreCase("DESC"))) {
					sortStatement.append(params.get("sort") + " DESC");
				}
				else {
					sortStatement.append(params.get("sort"));
				}
			}
		}
		else {
			sortStatement.append("title");
		}	
		query.append(sortStatement);
		System.err.println(query.toString());				//PRINT STATEMENT
		return query.toString();
	}
	
	private static boolean fieldExists(String field) {
		try {
		    @SuppressWarnings("unused")
		    Field f = VideoRelease.class.getDeclaredField(field);
		    return true;
		}
		catch (NoSuchFieldException ex) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "VideoRelease [id=" + id + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
				+ released + ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer="
				+ writer + ", actors=" + actors + ", plot=" + plot + ", language=" + language + ", country=" + country
				+ ", awards=" + awards + ", poster=" + poster + ", metascore=" + metascore + ", imdbRating="
				+ imdbRating + ", imdbVotes=" + imdbVotes + ", imdbID=" + imdbID + ", type=" + type + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public Integer getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(Integer imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getUserAvgRating() {
		return userAvgRating;
	}

	public void setUserAvgRating(Double userAvgRating) {
		this.userAvgRating = userAvgRating;
	}

	public Integer getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(Integer numVotes) {
		this.numVotes = numVotes;
	}
}