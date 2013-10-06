package be.rochus.faces.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.ScheduleEvent;

import be.rochus.domain.Event;
import be.rochus.domain.type.EventType;
import be.rochus.domain.type.Eventeable;
import be.rochus.faces.events.EventConverter;

public class EventConverterTest {

	private EventConverter converter;
	
	@Before
	public void setup() {
		converter = new EventConverter();
	}
	
	@Test
	public void testConversion() {
		Date from = new DateTime(2012, 9, 3, 15, 0).toDate();
		Date to = new DateTime(2012, 9, 3, 17, 30).toDate();

		Eventeable testData = new TestEventeable("blabla");
		
		Event event = new Event();
		event.setAllDay(true);
		event.setStartDate(from);
		event.setEndDate(to);
		event.setTitle("Some event");
		event.setType(EventType.BIRTHDAY);
		event.setData(testData);
		
		ScheduleEvent primeEvent = converter.toPrimeEvent(event);
		assertEquals("Some event", primeEvent.getTitle());
		assertTrue(primeEvent.isAllDay());
		assertFalse(primeEvent.isEditable());
		assertEquals("birthday", primeEvent.getStyleClass());
		assertEquals(from, primeEvent.getStartDate());
		assertEquals(to, primeEvent.getEndDate());
		assertEquals(testData, primeEvent.getData());
	}
	
	class TestEventeable implements Eventeable {
		
		private String id;
		
		public TestEventeable(String id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TestEventeable other = (TestEventeable) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		private EventConverterTest getOuterType() {
			return EventConverterTest.this;
		}
		
	}
	
}
