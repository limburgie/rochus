package be.rochus.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class GetRelativeDateBetweenTest {

	private Date from = new DateTime(2012, 8, 5, 12, 0).toDate();
	private Date to = new DateTime(2012, 10, 5, 12, 0).toDate();
	
	@Test
	public void testValidDateReturnsNewRelativeDate() {
		Date dateToCheck = new DateTime(1998, 9, 7, 12, 0).toDate();
		Date expected = new DateTime(2012, 9, 7, 12, 0).toDate();
		Date actual = DateUtils.getRelativeDateBetween(dateToCheck, from, to);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testInvalidDateReturnsNull() {
		Date dateToCheck = new DateTime(1998, 4, 7, 12, 0).toDate();
		Date actual = DateUtils.getRelativeDateBetween(dateToCheck, from, to);
		assertNull(actual);
	}
	
	@Test
	public void testDateAfterEndDateReturnsNull(){
		Date dateToCheck = new DateTime(2012, 10, 6, 12, 0).toDate();
		Date actual = DateUtils.getRelativeDateBetween(dateToCheck, from, to);
		assertNull(actual);
	}
	
}
