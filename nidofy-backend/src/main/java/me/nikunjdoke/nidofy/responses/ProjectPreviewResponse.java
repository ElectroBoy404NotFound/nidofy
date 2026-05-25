package me.nikunjdoke.nidofy.responses;

import java.util.Date;

public class ProjectPreviewResponse {
	private String title;
	private String description;
	private String thumbnail;
	private String languages;
	private String timeperiod;
	private long id;
	private String date;
	private Date createdAt;
	
	public ProjectPreviewResponse() {
		super();
	}
	
	public ProjectPreviewResponse(String title, String description, String thumbnail, String launuage,
			String timeperiod, long id, String date, Date createdAt) {
		super();
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
		this.languages = launuage;
		this.timeperiod = timeperiod;
		this.id = id;
		this.date = date;
		this.createdAt = createdAt;
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
	 * @return the language
	 */
	public String getLanguages() {
		return languages;
	}
	/**
	 * @param launuage the language to set
	 */
	public void setLanguages(String language) {
		this.languages = language;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
