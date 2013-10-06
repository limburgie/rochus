package be.rochus.service;

import java.util.List;

import be.rochus.domain.Schietstand;
import be.rochus.domain.Schutterij;
import be.rochus.domain.type.Series;
import be.rochus.service.exception.NoSuchAddressException;
import be.rochus.service.exception.NoSuchSchietstandException;

public interface SchutterijService {

	List<Schutterij> getSchutterijen(Series series);

	Schutterij save(Schutterij schutterij, boolean eigenSchietstand) throws NoSuchAddressException;

	Schutterij getSchutterij(Long id);

	List<Schutterij> getSchutterijenMetEigenSchietstand();
	
	Schietstand getSchietstand(Long id) throws NoSuchSchietstandException;

	List<Schutterij> getSchutterijen();
	
	Schutterij getSchutterij(String urlTitle);
	
}
