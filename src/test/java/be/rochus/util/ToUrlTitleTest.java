package be.rochus.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ToUrlTitleTest {

	@Test
	public void testSchutter() {
		assertEquals("helene-ramaekers", StringUtils.toUrlTitle("Hélène Ramaekers"));
	}
	
	@Test
	public void testSchutterij() {
		assertEquals("st-rochus-zutendaal", StringUtils.toUrlTitle("St.-Rochus Zutendaal"));
	}
	
	@Test
	public void testNonAlphaNumerics() {
		assertEquals("profielfotos", StringUtils.toUrlTitle("Profielfoto's"));
	}
	
}
