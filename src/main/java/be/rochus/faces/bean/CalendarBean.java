package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.map.MapModel;
import org.springframework.context.annotation.Scope;

import be.rochus.domain.Event;
import be.rochus.domain.Gebeurtenis;
import be.rochus.domain.Schutter;
import be.rochus.domain.Wedstrijd;
import be.rochus.faces.events.EventConverter;
import be.rochus.faces.maps.MapModelFactory;
import be.rochus.service.EventService;

@Named @Scope("view")
public class CalendarBean implements Serializable {

	@Inject private EventService eventService;
	@Inject private EventConverter eventConverter;
	@Inject private MapModelFactory mapModelFactory;
	
	private ScheduleModel scheduleModel;
	private ScheduleEvent selectedEvent;
	private MapModel mapModel;
	
	@PostConstruct
	public void init() {
		scheduleModel = new LazyScheduleModel() {
			@Override
			public void loadEvents(Date start, Date end) {
				for(Event event: eventService.getEvents(start, end)) {
					addEvent(eventConverter.toPrimeEvent(event));
				}
			}
		};
	}
	
	public void selectEvent(ScheduleEntrySelectEvent selectEvent) {
		selectedEvent = selectEvent.getScheduleEvent();
		if(isGame()) {
			mapModel = mapModelFactory.fromSchutterij(getWedstrijd().getOrganizer());
		}
	}
	
	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public ScheduleEvent getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(ScheduleEvent selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
	
	public boolean isBirthday() {
		return selectedEvent != null && selectedEvent.getData() instanceof Schutter;
	}
	
	public boolean isGame() {
		return selectedEvent != null && selectedEvent.getData() instanceof Wedstrijd;
	}
	
	public boolean isEvent() {
		return selectedEvent != null && selectedEvent.getData() instanceof Gebeurtenis;
	}
	
	public Schutter getSchutter() {
		return (Schutter) selectedEvent.getData();
	}
	
	public Wedstrijd getWedstrijd() {
		return (Wedstrijd) selectedEvent.getData();
	}
	
	public Gebeurtenis getGebeurtenis() {
		return (Gebeurtenis) selectedEvent.getData();
	}
	
	public String getLabel() {
		if(isBirthday()) {
			return "Verjaardag";
		}
		if(isGame()) {
			return "Wedstrijd";
		}
		return "Evenement";
	}
	
	public MapModel getMapModel() {
		return mapModel;
	}
	
}
