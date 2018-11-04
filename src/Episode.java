public class Episode {
	
	String season;
	String episode;
	String title;
	String airDate;
	
	public Episode(String season, String episode, String title, String airDate) {
		super();
		this.season = season;
		this.episode = episode;
		this.title = title;
		this.airDate = airDate;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getEpisode() {
		return episode;
	}

	public String getAirDate() {
		return airDate;
	}
	
	public void setAirDate(String airDate) {
		this.airDate = airDate;
	}
	
	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
