package be.rochus.faces.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.joda.time.DateTime;

import be.rochus.domain.type.Ploeg;
import be.rochus.domain.type.Series;
import be.rochus.domain.type.WedstrijdType;

@Named
public class ConstantsBean {
	
	private List<WedstrijdType> types = Arrays.asList(WedstrijdType.values());
	private List<Series> series = Arrays.asList(Series.values());
	private List<Integer> bomen = Arrays.asList(1, 2, 3, 4, 5);
	private List<Ploeg> ploegen = Arrays.asList(Ploeg.values());
	private Locale locale = new Locale("nl", "BE");
	private List<Integer> years;
	
	private static final int START_YEAR = 1980;
	
	@PostConstruct
	public void init() {
		initYears();
	}
	
	private void initYears() {
		years = new ArrayList<Integer>();
		for(int i=START_YEAR; i<=new DateTime().getYear(); i++) {
			years.add(i);
		}
	}

	public List<WedstrijdType> getWedstrijdTypes() {
		return types;
	}
	
	public List<Series> getSeries() {
		return series;
	}
	
	public List<Integer> getBomen() {
		return bomen;
	}
	
	public List<Ploeg> getPloegen() {
		return ploegen;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public List<Integer> getYears() {
		return years;
	}
	
}
