package be.rochus.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import be.rochus.domain.type.Eventeable;
import be.rochus.domain.type.WedstrijdType;

@Entity
public class Wedstrijd implements Eventeable {

	@Id @GeneratedValue
	private Long id;
	private Date date;
	@OneToOne
	private Schutterij organizer;
	private WedstrijdType type;

	public String getTitle() {
		return String.format("%s %s", type.getName(), organizer.getName());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Schutterij getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Schutterij organizer) {
		this.organizer = organizer;
	}

	public WedstrijdType getType() {
		return type;
	}

	public void setType(WedstrijdType type) {
		this.type = type;
	}

}
