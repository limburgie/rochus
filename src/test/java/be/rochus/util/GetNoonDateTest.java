package be.rochus.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class GetNoonDateTest {

	@Test
	public void testMidnightDate() {
		Date in = new DateTime(2012, 9, 5, 0, 0).toDate();
		Date expected = new DateTime(2012, 9, 5, 12, 0).toDate();
		Date actual = DateUtils.getNoonDate(in);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNullDate() {
		assertNull(DateUtils.getNoonDate(null));
	}
	
}
