package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;

import be.rochus.domain.Gebeurtenis;
import be.rochus.faces.FacesUtil;
import be.rochus.service.GebeurtenisService;

@Named @Scope("view")
public class EventBean implements Serializable {

	@Inject private GebeurtenisService eventService;
	@Inject private AccountBean accountBean;
	@Inject private FacesUtil facesUtil;
	
	private Gebeurtenis event;
	private List<Gebeurtenis> events;
	private Date startTime;
	private Date endTime;
	
	public void prepareNew() {
		event = new Gebeurtenis();
	}
	
	public void setCurrentEvent(long eventId) {
		event = eventService.getEvent(eventId);
	}

	public void save() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		updateTimes();
		eventService.save(event);
		initEvents();
		facesUtil.info("Evenement werd succesvol opgeslagen!");
		facesUtil.closeDialog();
	}
	
	public void delete() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		eventService.delete(event);
		initEvents();
		facesUtil.info("Evenement werd succesvol verwijderd!");
	}
	
	public List<Gebeurtenis> getEvents() {
		if(events == null) {
			initEvents();
		}
		return events;
	}

	public Gebeurtenis getEvent() {
		return event;
	}

	public void setEvent(Gebeurtenis event) {
		this.event = event;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	private void updateTimes() {
		Date startDate = getDateTime(event.getStartDate(), startTime);
		Date endDate = getDateTime(event.getEndDate(), endTime);
		event.setStartDate(startDate);
		event.setEndDate(endDate);
	}
	
	private Date getDateTime(Date date, Date time) {
		if(date == null) {
			return null;
		}
		DateTime d = new DateTime(date);
		DateTime t = new DateTime(time);
		d = d.withHourOfDay(t.getHourOfDay()).withMinuteOfHour(t.getMinuteOfHour());
		return d.toDate();
	}

	private void initEvents() {
		events = eventService.getEvents();
	}
	
}
