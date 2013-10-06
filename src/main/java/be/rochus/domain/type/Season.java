package be.rochus.domain.type;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class Season {

	private static final DateTime REFERENCE_START_DATE = new DateTime(1900, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);
	private static final DateTime REFERENCE_END_DATE = new DateTime(1900, 12, 31, 23, 59, 59, 999, DateTimeZone.UTC);

	private int year;
	private Date startDate;
	private Date endDate;
	
	public Season() {
		this(new DateTime().getYear());
	}
	
	public Season(int year) {
		this.year = year;
		startDate = REFERENCE_START_DATE.withYear(year).toDate();
		endDate = REFERENCE_END_DATE.withYear(year).toDate();
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public int getYear() {
		return year;
	}
	
}
