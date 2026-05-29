package me.nikunjdoke.nidofy.models;

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

@Table(name = "poems")
@Entity
public class Poem {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

	@Column
	private String title;
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "JSON")
	private List<List<String>> poem;
	@Column
	private String date;
	@Column
	private String signature;
	@Column()
	private Integer signatureLength;
	
	@CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
	
	public Poem(String title, List<List<String>> poem, String date, String signature, Integer signatureLength) {
		super();
		
		this.title = title;
		this.poem = poem;
		this.date = date;
		this.signature = signature;
		this.signatureLength = signatureLength;
	}
	
	public Poem() {
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

	/**
	 * @return the signatureLength
	 */
	public Integer getSignatureLength() {
		return signatureLength;
	}

	/**
	 * @param signatureLength the signatureLength to set
	 */
	public void setSignatureLength(Integer signatureLength) {
		this.signatureLength = signatureLength;
	}
}