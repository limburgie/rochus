package be.rochus.service;

import java.util.List;

import be.rochus.domain.Koningskoppel;
import be.rochus.domain.Schutter;
import be.rochus.domain.type.KoningsPartner;

public interface KoningskoppelService {

	List<Koningskoppel> getKoningskoppels();

	Koningskoppel getKoppel(Long id);

	void save(Koningskoppel koppel);

	void delete(Koningskoppel koppel);
	
	List<KoningsPartner> getKoningsPartners(Schutter schutter);
	
	Koningskoppel getKoppelInJaar(int jaar);
	
}
