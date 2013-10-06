package be.rochus.service;

import java.util.Date;
import java.util.List;

import be.rochus.domain.Gebeurtenis;

public interface GebeurtenisService {

	Gebeurtenis getEvent(long eventId);

	void save(Gebeurtenis event);

	List<Gebeurtenis> getEvents();

	void delete(Gebeurtenis event);

	List<Gebeurtenis> getEventsBetween(Date from, Date to);

}
