package be.rochus.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import be.rochus.domain.type.Series;

@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"name"}),
		@UniqueConstraint(columnNames={"urlTitle"})
})
public class Schutterij {

	@Id @GeneratedValue 
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="schietstand")
	private Schietstand schietstand = new Schietstand();
	
	private String website;
	
	@Enumerated(EnumType.STRING)
	private Series series;
	
	private String urlTitle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEigenSchietstand() {
		return getSchietstand().getEigenaar().equals(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Schietstand getSchietstand() {
		return schietstand;
	}

	public void setSchietstand(Schietstand schietstand) {
		this.schietstand = schietstand;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schutterij other = (Schutterij) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
