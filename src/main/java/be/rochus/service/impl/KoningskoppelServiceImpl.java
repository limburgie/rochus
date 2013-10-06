package be.rochus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import be.rochus.domain.Koningskoppel;
import be.rochus.domain.Schutter;
import be.rochus.domain.type.KoningsPartner;
import be.rochus.repository.KoningskoppelRepository;
import be.rochus.service.KoningskoppelService;

@Named
@Transactional(readOnly=true)
public class KoningskoppelServiceImpl implements KoningskoppelService {

	@Inject private KoningskoppelRepository repo;
	
	public List<Koningskoppel> getKoningskoppels() {
		return repo.findAll();
	}

	public Koningskoppel getKoppel(Long id) {
		return repo.findOne(id);
	}

	@Transactional
	public void save(Koningskoppel koppel) {
		repo.save(koppel);
	}

	@Transactional
	public void delete(Koningskoppel koppel) {
		repo.delete(koppel);
	}

	public List<KoningsPartner> getKoningsPartners(Schutter schutter) {
		List<Koningskoppel> koningsKoppels = repo.getKoningsKoppels(schutter);
		List<KoningsPartner> partners = new ArrayList<KoningsPartner>();
		for(Koningskoppel koppel: koningsKoppels) {
			KoningsPartner partner = new KoningsPartner();
			partner.setJaar(koppel.getJaar());
			if(schutter.equals(koppel.getKoning())) {
				partner.setPartnerName(koppel.getKoninginName());
			} else {
				partner.setPartnerName(koppel.getKoningName());
			}
			partners.add(partner);
		}
		return partners;
	}

	public Koningskoppel getKoppelInJaar(int jaar) {
		return repo.findByJaar(jaar);
	}

}
