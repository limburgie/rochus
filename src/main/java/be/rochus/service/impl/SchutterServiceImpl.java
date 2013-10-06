package be.rochus.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import be.rochus.domain.Schutter;
import be.rochus.repository.SchutterRepository;
import be.rochus.service.SchutterService;
import be.rochus.util.DateUtils;
import be.rochus.util.StringUtils;

@Named
@Transactional(readOnly = true)
public class SchutterServiceImpl implements SchutterService, Serializable {

	@Inject private SchutterRepository repository;

	public List<Schutter> getSchutters() {
		return repository.findSchutters();
	}

	public List<Schutter> getActiveSchutters() {
		return repository.findActive();
	}

	public List<Schutter> getSchuttersWithBirthdayBetween(Date startDate, Date endDate) {
		List<Schutter> result = new ArrayList<Schutter>();
		for (Schutter schutter : getSchutters()) {
			if(getBirthdayBetween(schutter, startDate, endDate) != null) {
				result.add(schutter);
			}
		}
		return result;
	}

	public List<Schutter> getSchuttersMaleOrFemale(boolean male) {
		return repository.findMaleOrFemale(male);
	}

	public Date getBirthdayBetween(Schutter schutter, Date startDate, Date endDate) {
		return DateUtils.getRelativeDateBetween(schutter.getBirthDate(), startDate, endDate);
	}

	public Schutter getSchutter(Long id) {
		return repository.findOne(id);
	}

	@Transactional
	public Schutter save(Schutter schutter) {
		schutter.setUrlTitle(StringUtils.toUrlTitle(schutter.getFullName()));
		return repository.save(schutter);
	}

	@Transactional
	public void delete(Schutter schutter) {
		repository.delete(schutter);
	}

	public Schutter getSchutter(String urlTitle) {
		return repository.findByUrlTitle(urlTitle);
	}

}
