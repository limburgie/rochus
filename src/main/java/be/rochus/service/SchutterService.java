package be.rochus.service;

import java.util.Date;
import java.util.List;

import be.rochus.domain.Schutter;

public interface SchutterService {

	List<Schutter> getSchutters();
	
	List<Schutter> getActiveSchutters();
	
	List<Schutter> getSchuttersMaleOrFemale(boolean male);
	
	Schutter getSchutter(Long id);
	
	Schutter getSchutter(String urlTitle);

	Schutter save(Schutter schutter);

	List<Schutter> getSchuttersWithBirthdayBetween(Date startDate, Date endDate);
	
	Date getBirthdayBetween(Schutter schutter, Date startDate, Date endDate);

	void delete(Schutter schutter);

}
