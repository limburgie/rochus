package be.rochus.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.rochus.domain.Event;
import be.rochus.domain.Schutter;
import be.rochus.domain.type.EventType;
import be.rochus.service.SchutterService;

@RunWith(MockitoJUnitRunner.class)
public class EventFromSchutterTest {

	@Mock private SchutterService schutterService;
	@InjectMocks private EventFactory factory;
	private Schutter schutter;
	
	@Before
	public void setup() {
		schutter = new Schutter();
		schutter.setId(111l);
	}
	
	@Test
	public void testFactory() {
		Date from = new DateTime(2012, 9, 8, 7, 00).toDate();
		Date to = new DateTime(2012, 11, 8, 7, 00).toDate();
		Date relative = new DateTime(2012, 10, 8, 7, 00).toDate();
		Date actual = new DateTime(2012, 10, 8, 12, 0).toDate();

		when(schutterService.getBirthdayBetween(schutter, from, to)).thenReturn(relative);
		
		DateTime birthDayDateTime = new DateTime(1984, 12, 20, 10, 15);
		Date birthDay = birthDayDateTime.toDate();

		schutter.setBirthDate(birthDay);
		schutter.setFirstName("Peter");
		
		Event event = factory.fromSchutter(schutter, from, to);
		assertEquals(actual, event.getStartDate());
		assertEquals(actual, event.getEndDate());
		assertTrue(event.isAllDay());
		assertEquals("Peter wordt 27!", event.getTitle());
		assertEquals(EventType.BIRTHDAY, event.getType());
		assertEquals(schutter, event.getData());
	}
	
}
