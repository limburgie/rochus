package be.rochus.service.impl;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;

import be.rochus.domain.Schietstand;
import be.rochus.domain.Schutterij;
import be.rochus.domain.type.Series;
import be.rochus.service.SchutterijService;
import be.rochus.service.exception.NoSuchAddressException;

public class GetSchutterijSchietstandTest extends ServiceTestCase {

	@Inject private SchutterijService schutterijService;

	@Test
	public void testCreateAndRetrieveSchutterij() throws NoSuchAddressException {
		Schietstand ss = new Schietstand();
		ss.setAddress("Teststraat");

		Schutterij s = new Schutterij();
		s.setName("Test");
		s.setSeries(Series.A);
		s.setSchietstand(ss);

		ss.setEigenaar(s);

		Schutterij sdb = schutterijService.save(s, true);

		Schutterij schutterij = schutterijService.getSchutterij(sdb.getId());
		assertTrue(schutterij.isEigenSchietstand());
	}

}
