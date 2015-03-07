package be.rochus.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DynamicImage {

	@Id @GeneratedValue
	private Long id;

	@Column(name = "image_key")
	private String key;

	@Column(name = "image_content") @Lob
	private byte[] content;

	private Date modifiedDate;

	public boolean isBloodyEmpty() {
		return content == null || content.length == 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
