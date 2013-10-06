package be.rochus.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class SchutterAgeTest {

	@Test
	public void testNegativeAge() {
		Schutter s = new Schutter();
		s.setBirthDate(new DateTime(1984, 12, 20, 10, 15).toDate());
		Date now = new DateTime(1983, 9, 1, 7, 41).toDate();
		
		assertEquals(0, s.getAgeOnDate(now));
	}
	
	@Test
	public void testPositiveAge() {
		Schutter s = new Schutter();
		s.setBirthDate(new DateTime(1984, 12, 20, 10, 15).toDate());
		Date now = new DateTime(2013, 9, 1, 7, 41).toDate();
		
		assertEquals(28, s.getAgeOnDate(now));
	}
	
	@Test
	public void testAgeOnDateItself() {
		Schutter s = new Schutter();
		s.setBirthDate(new DateTime(1984, 12, 20, 10, 15).toDate());
		Date now = new DateTime(2012, 12, 20, 10, 16).toDate();
		
		assertEquals(28, s.getAgeOnDate(now));
	}
	
}
