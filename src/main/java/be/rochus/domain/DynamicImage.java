package be.rochus.domain;

import javax.persistence.*;

@Entity
public class DynamicImage {

	@Id @GeneratedValue
	private Long id;

	@Column(name = "image_key")
	private String key;

	@Column(name = "image_content") @Lob
	private byte[] content;

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

}
