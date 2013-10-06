package be.rochus.util;

import java.util.Date;

import org.joda.time.DateTime;

public class DateUtils {

	public static Date getStartMonthDate(Date from, Date to) {
		DateTime fromDate = new DateTime(from);
		DateTime toDate = new DateTime(to);
		return getAverageDate(fromDate, toDate).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).toDate();
	}
	
	public static Date getEndMonthDate(Date from, Date to) {
		DateTime fromDate = new DateTime(from);
		DateTime toDate = new DateTime(to);
		DateTime average = getAverageDate(fromDate, toDate);
		return average.withDayOfMonth(average.dayOfMonth().getMaximumValue()).withHourOfDay(23).withMinuteOfHour(59).toDate();
	}
	
	private static DateTime getAverageDate(DateTime from, DateTime to) {
		return new DateTime((to.getMillis()+from.getMillis())/2);
	}
	
	public static Date getRelativeDateBetween(Date original, Date startDate, Date endDate) {
		DateTime start = new DateTime(startDate);
		DateTime end = new DateTime(endDate);
		DateTime orig = new DateTime(original);
		if (orig.isBefore(end)) {
			for (int i = start.getYear(); i <= end.getYear(); i++) {
				DateTime movedBirthDate = orig.withYear(i);
				if (movedBirthDate.isAfter(start) && movedBirthDate.isBefore(end)) {
					return movedBirthDate.toDate();
				}
			}
		}
		return null;
	}
	
	public static Date getNoonDate(Date date) {
		if(date == null) {
			return null;
		}
		return new DateTime(date).withHourOfDay(12).toDate();
	}
	
}
