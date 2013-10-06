package be.rochus.service;

import java.util.Date;
import java.util.List;

import be.rochus.domain.Event;

public interface EventService {

	public List<Event> getEvents(Date startDate, Date endDate);
	
}
