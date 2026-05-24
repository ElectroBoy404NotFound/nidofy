package me.electronicsboy.nidofy.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "projects")
@Entity
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String languages;
	@Column
	private String timeperiod;
	@Column
	private String date;
	@Column
	private String thumbnail;
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "JSON")
	private List<String> explanation;
	
	@Column
	private String github;
	@Column
	private String youtube;
	@Column
	private String liveDemo;
	
	@CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
	
	public Project() {
		super();
	}

	public Project(Long id, String title, String description, String languages, String timeperiod, String date,
			String thumbnail, List<String> explanation, String github, String youtube, String liveDemo) {
		super();
		this.id = id;
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
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}