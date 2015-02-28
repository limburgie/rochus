package be.rochus.domain;

import javax.persistence.*;

@Entity
public class DynamicText {

	@Id @GeneratedValue
	private Long id;

	@Column(name="text_key", unique = true)
	private String key;

	@Column(name="text_content") @Lob
	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
