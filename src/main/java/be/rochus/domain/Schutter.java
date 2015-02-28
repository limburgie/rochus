package be.rochus.domain;

import be.rochus.domain.type.Eventeable;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints={
	@UniqueConstraint(columnNames={"firstName", "lastName"}),
	@UniqueConstraint(columnNames={"emailAddress"}),
	@UniqueConstraint(columnNames={"urlTitle"})
})
public class Schutter implements Eventeable {

	@Id @GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private boolean active;
	private boolean male;
	private Date birthDate;
	private String emailAddress;
	private int joinYear;
	private String urlTitle;
	
	public String getKoningLabel() {
		return male ? "koning" : "koningin";
	}
	
	public int getAgeOnDate(Date date) {
		DateMidnight bd = new DateMidnight(birthDate);
		DateTime now = new DateTime(date);
		if(now.isBefore(bd)) {
			return 0;
		}
		Years age = Years.yearsBetween(bd, now);
		return age.getYears();
	}
	
	public int getAge() {
		return getAgeOnDate(new DateTime().toDate());
	}
	
	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getJoinYear() {
		return joinYear;
	}

	public void setJoinYear(int joinYear) {
		this.joinYear = joinYear;
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
		Schutter other = (Schutter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
