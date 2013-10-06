package be.rochus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Schietstand {

	@Id @GeneratedValue
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private boolean autocalculateCoords;
	
	@OneToOne
	@JoinColumn(name="schutterij_owner")
	private Schutterij eigenaar;
	
	public String getCoordinates() {
		return String.format("%s, %s", latitude, longitude);
	}
	
	public boolean isCoordinatesValid() {
		return latitude != null && longitude != null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public boolean isAutocalculateCoords() {
		return autocalculateCoords;
	}

	public void setAutocalculateCoords(boolean autocalculateCoords) {
		this.autocalculateCoords = autocalculateCoords;
	}

	public Schutterij getEigenaar() {
		return eigenaar;
	}

	public void setEigenaar(Schutterij eigenaar) {
		this.eigenaar = eigenaar;
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
		Schietstand other = (Schietstand) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
