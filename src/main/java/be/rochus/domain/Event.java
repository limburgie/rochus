package be.rochus.domain;

import java.util.Date;

import be.rochus.domain.type.EventType;
import be.rochus.domain.type.Eventeable;

public class Event {

	private Date startDate;
	private Date endDate;
	private boolean allDay;
	private String title;
	private EventType type;
	private Eventeable data;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Eventeable getData() {
		return data;
	}

	public void setData(Eventeable data) {
		this.data = data;
	}

}
