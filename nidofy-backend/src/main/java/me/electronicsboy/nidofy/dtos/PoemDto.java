package me.electronicsboy.nidofy.dtos;

import java.util.List;

public class PoemDto {
	private String title;
    private List<List<String>> poem;
    private String date;
    private String signature;
    
	public PoemDto(String title, List<List<String>> poem, String date, String signature) {
		super();
		
		this.title = title;
		this.poem = poem;
		this.date = date;
		this.signature = signature;
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
	 * @return the poem
	 */
	public List<List<String>> getPoem() {
		return poem;
	}
	/**
	 * @param poem the poem to set
	 */
	public void setPoem(List<List<String>> poem) {
		this.poem = poem;
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
}