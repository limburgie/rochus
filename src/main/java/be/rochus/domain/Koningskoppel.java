package be.rochus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"jaar"}))
public class Koningskoppel {

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "koning_id")
	private Schutter koning;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "koningin_id")
	private Schutter koningin;
	
	private String koningEx;
	private String koninginEx;
	
	public boolean isCustomKoning() {
		return koning == null;
	}
	
	public boolean isCustomKoningin() {
		return koningin == null;
	}
	
	@NotNull
	private int jaar;

	@Lob
	private String thumbnailUrl;
	
	@Lob
	private String pictureUrl;

	public String getKoningName() {
		return koning == null ? koningEx : koning.getFullName();
	}
	
	public String getKoninginName() {
		return koningin == null ? koninginEx : koningin.getFullName();
	}
	
	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Schutter getKoning() {
		return koning;
	}

	public void setKoning(Schutter koning) {
		this.koning = koning;
	}

	public Schutter getKoningin() {
		return koningin;
	}

	public void setKoningin(Schutter koningin) {
		this.koningin = koningin;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getKoningEx() {
		return koningEx;
	}

	public void setKoningEx(String koningEx) {
		this.koningEx = koningEx;
	}

	public String getKoninginEx() {
		return koninginEx;
	}

	public void setKoninginEx(String koninginEx) {
		this.koninginEx = koninginEx;
	}

}
