package be.rochus.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import be.rochus.domain.Event;
import be.rochus.domain.Gebeurtenis;
import be.rochus.domain.type.EventType;

@RunWith(MockitoJUnitRunner.class)
public class EventFromGebeurtenisTest {

	private static final String TITLE = "Title";
	
	@InjectMocks private EventFactory factory;
	private Gebeurtenis gebeurtenis;
	
	@Before
	public void setup() {
		gebeurtenis = new Gebeurtenis();
		gebeurtenis.setId(111l);
		gebeurtenis.setTitle(TITLE);
	}
	
	@Test
	public void testNormalEvent() {
		Date start = new DateTime(2012, 9, 8, 7, 00).toDate();
		Date end = new DateTime(2012, 9, 8, 9, 00).toDate();
		gebeurtenis.setStartDate(start);
		gebeurtenis.setEndDate(end);
		
		Event event = factory.fromGebeurtenis(gebeurtenis);
		assertEquals(start, event.getStartDate());
		assertEquals(end, event.getEndDate());
		assertEquals(TITLE, event.getTitle());
		assertFalse(event.isAllDay());
		assertEquals(EventType.HAPPENING, event.getType());
		assertEquals(gebeurtenis, event.getData());
	}
	
	@Test
	public void testEventWithoutEndDate() {
		Date start = new DateTime(2012, 9, 8, 7, 00).toDate();
		gebeurtenis.setStartDate(start);
		gebeurtenis.setEndDate(null);
		
		Event event = factory.fromGebeurtenis(gebeurtenis);
		assertEquals(start, event.getStartDate());
		assertEquals(new DateTime(2012, 9, 8, 12, 00).toDate(), event.getEndDate());
		assertEquals(TITLE, event.getTitle());
		assertFalse(event.isAllDay());
		assertEquals(EventType.HAPPENING, event.getType());
		assertEquals(gebeurtenis, event.getData());
	}
	
	@Test
	public void testAlldayEvent() {
		Date start = new DateTime(2012, 9, 8, 7, 00).toDate();
		Date end = new DateTime(2012, 9, 8, 9, 00).toDate();
		gebeurtenis.setStartDate(start);
		gebeurtenis.setEndDate(end);
		gebeurtenis.setAllDay(true);
		
		Event event = factory.fromGebeurtenis(gebeurtenis);
		assertEquals(start, event.getStartDate());
		assertEquals(end, event.getEndDate());
		assertEquals(TITLE, event.getTitle());
		assertTrue(event.isAllDay());
		assertEquals(EventType.HAPPENING, event.getType());
		assertEquals(gebeurtenis, event.getData());
	}
	
	@Test
	public void testAlldayEventWithoutEndDate() {
		Date start = new DateTime(2012, 9, 8, 7, 00).toDate();
		gebeurtenis.setStartDate(start);
		gebeurtenis.setEndDate(null);
		gebeurtenis.setAllDay(true);
		
		Event event = factory.fromGebeurtenis(gebeurtenis);
		assertEquals(start, event.getStartDate());
		assertEquals(new DateTime(2012, 9, 8, 12, 00).toDate(), event.getEndDate());
		assertEquals(TITLE, event.getTitle());
		assertTrue(event.isAllDay());
		assertEquals(EventType.HAPPENING, event.getType());
		assertEquals(gebeurtenis, event.getData());
	}
	
}
