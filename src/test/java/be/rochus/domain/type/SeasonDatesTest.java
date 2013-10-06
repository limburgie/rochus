package be.rochus.domain.type;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class SeasonDatesTest {

	@Test
	public void testSampleYear() {
		Season season = new Season(2011);
		Date expectedStart = new DateTime("2011-01-01T00:00:00.000+00:00").toDate();
		Date expectedEnd = new DateTime("2011-12-31T23:59:59.999+00:00").toDate();
		assertEquals(expectedStart, season.getStartDate());
		assertEquals(expectedEnd, season.getEndDate());
	}
	
}
