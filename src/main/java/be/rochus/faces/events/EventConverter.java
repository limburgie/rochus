package be.rochus.faces.events;

import javax.inject.Named;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

import be.rochus.domain.Event;

@Named
public class EventConverter {

	public ScheduleEvent toPrimeEvent(Event event) {
		DefaultScheduleEvent result = new DefaultScheduleEvent();
		result.setAllDay(event.isAllDay());
		result.setData(event.getData());
		result.setEditable(false);
		result.setEndDate(event.getEndDate());
		result.setId(null);
		result.setStartDate(event.getStartDate());
		result.setTitle(event.getTitle());
		result.setStyleClass(event.getType().name().toLowerCase());
		return result;
	}
	
}
