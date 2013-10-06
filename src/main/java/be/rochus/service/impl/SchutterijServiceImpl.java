package be.rochus.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

import be.rochus.domain.Schietstand;
import be.rochus.domain.Schutterij;
import be.rochus.domain.type.Series;
import be.rochus.repository.SchietstandRepository;
import be.rochus.repository.SchutterijRepository;
import be.rochus.service.SchutterijService;
import be.rochus.service.exception.NoSuchAddressException;
import be.rochus.service.exception.NoSuchSchietstandException;
import be.rochus.util.StringUtils;

@Named
@Transactional(readOnly=true)
public class SchutterijServiceImpl implements SchutterijService, Serializable {

	@Inject private SchutterijRepository schutterijRepo;
	@Inject private SchietstandRepository schietstandRepo;

	public List<Schutterij> getSchutterijen() {
		return schutterijRepo.findAll();
	}
	
	public List<Schutterij> getSchutterijen(Series series) {
		return schutterijRepo.findBySeries(series);
	}

	@Transactional
	public Schutterij save(Schutterij schutterij, boolean eigen) throws NoSuchAddressException {
		Schietstand schietstand = schutterij.getSchietstand();
		if(eigen) {
			if(schietstand.isAutocalculateCoords()) {
				LatLng position = getGeoposition(schietstand.getAddress());
				schietstand.setLatitude(position.getLat().doubleValue());
				schietstand.setLongitude(position.getLng().doubleValue());
			}
			schietstand = schietstandRepo.save(schietstand);
			schietstand.setEigenaar(schutterij);
		}
		schutterij.setUrlTitle(StringUtils.toUrlTitle(schutterij.getName()));
		return schutterijRepo.save(schutterij);
	}
	
	private LatLng getGeoposition(String address) throws NoSuchAddressException {
		Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("nl").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		if(geocoderResponse != null && geocoderResponse.getStatus() == GeocoderStatus.OK && !geocoderResponse.getResults().isEmpty()) {
			GeocoderResult result = geocoderResponse.getResults().get(0);
			return result.getGeometry().getLocation();
		}
		throw new NoSuchAddressException();
	}

	public Schutterij getSchutterij(Long id) {
		return schutterijRepo.findOne(id);
	}
	
	public Schutterij getSchutterij(String urlTitle) {
		return schutterijRepo.findByUrlTitle(urlTitle);
	}

	public List<Schutterij> getSchutterijenMetEigenSchietstand() {
		List<Schutterij> result = new ArrayList<Schutterij>();
		for(Schutterij s: getSchutterijen()) {
			if(s.isEigenSchietstand()) {
				result.add(s);
			}
		}
		return result;
	}

	public Schietstand getSchietstand(Long id) throws NoSuchSchietstandException {
		return schietstandRepo.findOne(id);
	}
	
}
