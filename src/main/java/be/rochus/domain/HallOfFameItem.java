package be.rochus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class HallOfFameItem {

	@Id @GeneratedValue 
	private Long id;
	
	@NotNull
	private int jaar;
	
	private String prijs;
	private String behaaldDoor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public String getPrijs() {
		return prijs;
	}

	public void setPrijs(String prijs) {
		this.prijs = prijs;
	}

	public String getBehaaldDoor() {
		return behaaldDoor;
	}

	public void setBehaaldDoor(String behaaldDoor) {
		this.behaaldDoor = behaaldDoor;
	}

}
