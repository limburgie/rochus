package be.rochus.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import be.rochus.domain.Event;
import be.rochus.domain.Gebeurtenis;
import be.rochus.domain.Schutter;
import be.rochus.domain.Wedstrijd;
import be.rochus.factory.EventFactory;
import be.rochus.service.EventService;
import be.rochus.service.GebeurtenisService;
import be.rochus.service.SchutterService;
import be.rochus.service.WedstrijdService;
import be.rochus.util.DateUtils;

@Named
public class EventServiceImpl implements EventService {

	@Inject private SchutterService schutterService;
	@Inject private WedstrijdService wedstrijdService;
	@Inject private GebeurtenisService gebeurtenisService;
	@Inject private EventFactory eventFactory;
	
	public List<Event> getEvents(Date from, Date to) {
		Date fromMonth = DateUtils.getStartMonthDate(from, to);
		Date toMonth = DateUtils.getEndMonthDate(from, to);
		
		List<Event> events = new ArrayList<Event>();
		
		addSchutterBirthdays(fromMonth, toMonth, events);
		addWedstrijden(fromMonth, toMonth, events);
		addGebeurtenissen(fromMonth, toMonth, events);
		
		return events;
	}

	private void addGebeurtenissen(Date from, Date to, List<Event> events) {
		List<Gebeurtenis> gebeurtenissen = gebeurtenisService.getEventsBetween(from, to);
		for(Gebeurtenis gebeurtenis: gebeurtenissen) {
			events.add(eventFactory.fromGebeurtenis(gebeurtenis));
		}
	}

	private void addWedstrijden(Date from, Date to, List<Event> events) {
		List<Wedstrijd> wedstrijden = wedstrijdService.getWedstrijdenBetween(from, to);
		for(Wedstrijd wedstrijd: wedstrijden) {
			events.add(eventFactory.fromWedstrijd(wedstrijd));
		}
	}

	private void addSchutterBirthdays(Date from, Date to, List<Event> events) {
		List<Schutter> schutters = schutterService.getSchuttersWithBirthdayBetween(from, to);
		for(Schutter schutter: schutters) {
			if(schutter.getBirthDate() != null) {
				events.add(eventFactory.fromSchutter(schutter, from, to));
			}
		}
	}

}
