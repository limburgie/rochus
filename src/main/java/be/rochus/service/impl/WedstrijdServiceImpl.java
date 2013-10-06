package be.rochus.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import be.rochus.domain.Wedstrijd;
import be.rochus.domain.type.Season;
import be.rochus.repository.WedstrijdRepository;
import be.rochus.service.WedstrijdService;

@Named
@Transactional(readOnly=true)
public class WedstrijdServiceImpl implements WedstrijdService {

	@Inject private WedstrijdRepository repository;
	
	public List<Wedstrijd> getUpcoming() {
		Season season = new Season();
		Date from = new Date();
		Date to = season.getEndDate();
		return getWedstrijdenBetween(from, to);
	}
	
	public List<Wedstrijd> getWedstrijden() {
		Season season = new Season();
		return getWedstrijdenBetween(season.getStartDate(), season.getEndDate());
	}

	public Wedstrijd getWedstrijd(long id) {
		return repository.findOne(id);
	}

	@Transactional
	public void save(Wedstrijd wedstrijd) {
		repository.save(wedstrijd);
	}

	@Transactional
	public List<Wedstrijd> getWedstrijdenBetween(Date from, Date to) {
		return repository.getGamesBetween(from, to);
	}

	@Transactional
	public void delete(Wedstrijd wedstrijd) {
		repository.delete(wedstrijd);
	}

}
