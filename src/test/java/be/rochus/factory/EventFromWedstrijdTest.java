package be.rochus.factory;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import be.rochus.domain.Event;
import be.rochus.domain.Schutterij;
import be.rochus.domain.Wedstrijd;
import be.rochus.domain.type.EventType;
import be.rochus.domain.type.WedstrijdType;

@RunWith(MockitoJUnitRunner.class)
public class EventFromWedstrijdTest {

	@InjectMocks private EventFactory factory;
	
	private Wedstrijd wedstrijd;
	
	@Before
	public void setup() {
		wedstrijd = new Wedstrijd();
		wedstrijd.setId(222l);
	}
	
	@Test
	public void testConversion() {
		Date from = new DateTime(2012, 6, 8, 12, 0).toDate();
		wedstrijd.setDate(from);
		Schutterij schutterij = new Schutterij();
		schutterij.setName("Test");
		wedstrijd.setOrganizer(schutterij);
		wedstrijd.setType(WedstrijdType.OFFICIEEL);
		
		Event event = factory.fromWedstrijd(wedstrijd);
		
		assertEquals("Officiële wedstrijd Test", event.getTitle());
		assertEquals(from, event.getStartDate());
		assertEquals(new DateTime(2012, 6, 8, 16, 0).toDate(), event.getEndDate());
		assertEquals(EventType.WEDSTRIJD, event.getType());
		assertEquals(wedstrijd, event.getData());
	}
	
}
