package me.nikunjdoke.nidofy.responses;

import java.util.Date;

public class PoemPreviewResponse {
	private String title;
	private String content;
	private String signature;
	private long id;
	private String date;
	private Date createdAt;
	
	public PoemPreviewResponse(String title, String content, String signature, long id, String date, Date createdAt) {
		super();
		this.title = title;
		this.content = content;
		this.signature = signature;
		this.id = id;
		this.date = date;
		this.setCreatedAt(createdAt);
	}
	
	public PoemPreviewResponse() {
		super();
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
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
