package be.rochus.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class GetMonthBoundariesDate {

	@Test
	public void testMarch() {
		Date originalStart = new Date(1361743200000L); //Sun Feb 24 22:00:00 GMT 2013
		Date originalEnd = new Date(1365368400000L); //Sun Apr 07 21:00:00 GMT 2013
		
		Date goalStart = new DateTime(2013, 3, 1, 0, 0).toDate();
		Date goalEnd = new DateTime(2013, 3, 31, 23, 59).toDate();
		
		assertEquals(goalStart, DateUtils.getStartMonthDate(originalStart, originalEnd));
		assertEquals(goalEnd, DateUtils.getEndMonthDate(originalStart, originalEnd));
	}
	
}
