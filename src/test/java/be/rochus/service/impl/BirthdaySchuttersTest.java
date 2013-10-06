package be.rochus.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import be.rochus.domain.Schutter;
import be.rochus.service.SchutterService;

public class BirthdaySchuttersTest {

	@Spy private SchutterService service = new SchutterServiceImpl();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDateLimitsInSameYear() {
		Schutter schutter = mock(Schutter.class);
		Date from = createDate("20/07/2012");
		Date to = createDate("05/09/2012");
		when(schutter.getBirthDate()).thenReturn(createDate("20/08/1984"));
		doReturn(Collections.singletonList(schutter)).when(service).getSchutters();
		
		List<Schutter> result = service.getSchuttersWithBirthdayBetween(from, to);
		assertEquals(schutter.getBirthDate(), result.get(0).getBirthDate());
	}
	
	@Test
	public void testDateLimitsInDifferentYears() {
		Schutter schutter1 = mock(Schutter.class);
		Schutter schutter2 = mock(Schutter.class);
		Schutter schutter3 = mock(Schutter.class);
		Date from = createDate("20/11/2012");
		Date to = createDate("05/01/2013");
		when(schutter1.getBirthDate()).thenReturn(createDate("20/12/1984"));
		when(schutter2.getBirthDate()).thenReturn(createDate("19/11/1957"));
		when(schutter3.getBirthDate()).thenReturn(createDate("06/01/1966"));
		doReturn(Arrays.asList(schutter1, schutter2, schutter3)).when(service).getSchutters();
		
		List<Schutter> result = service.getSchuttersWithBirthdayBetween(from, to);
		assertEquals(schutter1, result.get(0));
	}
	
	@Test
	public void testBirthdayAfterEndDateLimit() {
		Schutter schutter = mock(Schutter.class);
		Date from = createDate("20/07/2012");
		Date to = createDate("05/09/2012");
		when(schutter.getBirthDate()).thenReturn(createDate("06/08/2013"));
		doReturn(Collections.singletonList(schutter)).when(service).getSchutters();
		
		List<Schutter> result = service.getSchuttersWithBirthdayBetween(from, to);
		assertTrue(result.isEmpty());
	}
	
	private Date createDate(String date) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException();
		}
	}
	
}
