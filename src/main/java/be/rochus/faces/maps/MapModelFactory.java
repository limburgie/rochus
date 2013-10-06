package be.rochus.faces.maps;

import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import be.rochus.domain.Schietstand;
import be.rochus.domain.Schutterij;

@Named
public class MapModelFactory {

	public MapModel fromSchutterij(Schutterij schutterij) {
		MapModel mapModel = new DefaultMapModel();
		Schietstand schietstand = schutterij.getSchietstand();
		if(schietstand.isCoordinatesValid()) {
			mapModel.addOverlay(new Marker(new LatLng(schietstand.getLatitude(), schietstand.getLongitude())));
		}
		return mapModel;
	}
	
}
