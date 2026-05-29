package me.nikunjdoke.nidofy.dtos;

import java.util.List;

public class EditProjectDto {
	private Long id;
	
	private String title;
	private String description;
	private String languages;
	private String timeperiod;
	private String date;
	private String thumbnail;
	private List<String> explanation;
	
	private String github;
	private String youtube;
	private String liveDemo;
	
	public EditProjectDto() {
		super();
	}

	public EditProjectDto(Long id, String title, String description, String languages, String timeperiod, String date,
			String thumbnail, List<String> explanation, String github, String youtube, String liveDemo) {
		super();
		this.title = title;
		this.description = description;
		this.languages = languages;
		this.timeperiod = timeperiod;
		this.date = date;
		this.thumbnail = thumbnail;
		this.explanation = explanation;
		this.github = github;
		this.youtube = youtube;
		this.liveDemo = liveDemo;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the languages
	 */
	public String getLanguages() {
		return languages;
	}

	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(String languages) {
		this.languages = languages;
	}

	/**
	 * @return the timeperiod
	 */
	public String getTimeperiod() {
		return timeperiod;
	}

	/**
	 * @param timeperiod the timeperiod to set
	 */
	public void setTimeperiod(String timeperiod) {
		this.timeperiod = timeperiod;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * @return the explanation
	 */
	public List<String> getExplanation() {
		return explanation;
	}

	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(List<String> explanation) {
		this.explanation = explanation;
	}

	/**
	 * @return the github
	 */
	public String getGithub() {
		return github;
	}

	/**
	 * @param github the github to set
	 */
	public void setGithub(String github) {
		this.github = github;
	}

	/**
	 * @return the youtube
	 */
	public String getYoutube() {
		return youtube;
	}

	/**
	 * @param youtube the youtube to set
	 */
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	/**
	 * @return the liveDemo
	 */
	public String getLiveDemo() {
		return liveDemo;
	}

	/**
	 * @param liveDemo the liveDemo to set
	 */
	public void setLiveDemo(String liveDemo) {
		this.liveDemo = liveDemo;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}