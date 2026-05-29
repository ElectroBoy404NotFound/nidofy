package me.nikunjdoke.nidofy.dtos;

import java.util.List;

public class EditPoemDto {
	private long id;
	private String title;
    private List<List<String>> poem;
    private String date;
    private String signature;
    private int signatureLength;
    
	public EditPoemDto(long id, String title, List<List<String>> poem, String date, String signature, int signatureLength) {
		super();
		
		this.title = title;
		this.poem = poem;
		this.date = date;
		this.signature = signature;
		this.setSignatureLength(signatureLength);
	}
	
	public EditPoemDto() {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the signatureLength
	 */
	public int getSignatureLength() {
		return signatureLength;
	}

	/**
	 * @param signatureLength the signatureLength to set
	 */
	public void setSignatureLength(int signatureLength) {
		this.signatureLength = signatureLength;
	}
}