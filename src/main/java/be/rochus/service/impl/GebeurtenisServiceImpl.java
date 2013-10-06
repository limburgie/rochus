package be.rochus.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import be.rochus.domain.Gebeurtenis;
import be.rochus.repository.GebeurtenisRepository;
import be.rochus.service.GebeurtenisService;

@Named
public class GebeurtenisServiceImpl implements GebeurtenisService {

	@Inject private GebeurtenisRepository repo;
	
	public Gebeurtenis getEvent(long eventId) {
		return repo.findOne(eventId);
	}

	public void save(Gebeurtenis event) {
		repo.save(event);
	}

	public List<Gebeurtenis> getEvents() {
		return repo.getEvents();
	}

	public void delete(Gebeurtenis event) {
		repo.delete(event);
	}

	public List<Gebeurtenis> getEventsBetween(Date from, Date to) {
		return repo.getEventsBetween(from, to);
	}

}
