package be.rochus.service;

import java.util.Date;
import java.util.List;

import be.rochus.domain.Wedstrijd;

public interface WedstrijdService {

	List<Wedstrijd> getUpcoming();
	
	List<Wedstrijd> getWedstrijden();
	
	List<Wedstrijd> getWedstrijdenBetween(Date from, Date to);

	Wedstrijd getWedstrijd(long id);

	void save(Wedstrijd wedstrijd);

	void delete(Wedstrijd wedstrijd);
	
}
