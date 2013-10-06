package be.rochus.factory;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;

import be.rochus.domain.Event;
import be.rochus.domain.Gebeurtenis;
import be.rochus.domain.Schutter;
import be.rochus.domain.Wedstrijd;
import be.rochus.domain.type.EventType;
import be.rochus.service.SchutterService;
import be.rochus.util.DateUtils;

@Named
public class EventFactory {

	@Inject private SchutterService schutterService;
	
	public Event fromSchutter(Schutter schutter, Date from, Date to) {
		Date relativeBirthDate = schutterService.getBirthdayBetween(schutter, from, to);
		Date relativeNoonDate = DateUtils.getNoonDate(relativeBirthDate);
		
		Event event = new Event();
		event.setTitle(schutter.getFirstName()+" wordt "+schutter.getAgeOnDate(to)+"!");
		event.setAllDay(true);
		event.setStartDate(relativeNoonDate);
		event.setEndDate(relativeNoonDate);
		event.setType(EventType.BIRTHDAY);
		event.setData(schutter);
		return event;
	}

	public Event fromWedstrijd(Wedstrijd wedstrijd) {
		Event event = new Event();
		event.setTitle(wedstrijd.getTitle());
		event.setStartDate(wedstrijd.getDate());
		event.setEndDate(new DateTime(wedstrijd.getDate()).plusHours(4).toDate());
		event.setAllDay(false);
		event.setType(EventType.WEDSTRIJD);
		event.setData(wedstrijd);
		return event;
	}
	
	public Event fromGebeurtenis(Gebeurtenis gebeurtenis) {
		Event event = new Event();
		event.setTitle(gebeurtenis.getTitle());
		event.setStartDate(gebeurtenis.getStartDate());
		
		Date endDate = gebeurtenis.getEndDate();
		if(endDate == null) {
			endDate = new DateTime(gebeurtenis.getStartDate()).plusHours(5).toDate();
		}
		event.setEndDate(endDate);
		event.setAllDay(gebeurtenis.isAllDay());
		event.setType(EventType.HAPPENING);
		event.setData(gebeurtenis);
		return event;
	}
	
}
