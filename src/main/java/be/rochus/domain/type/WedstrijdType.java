package be.rochus.domain.type;

public enum WedstrijdType {

	OFFICIEEL("Officiële wedstrijd"), 
	OEFEN("Oefenwedstrijd"), 
	BEKER_VAN_BILZEN("Beker van Bilzen"), 
	PROVINCIAAL("Provinciale wedstrijd"), 
	JUWEEL_KAMPIOEN("Juweel Kampioen"), 
	JUWEEL_KAMPIOENE("Juweel Kampioene");
	
	private String name;
	
	private WedstrijdType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
