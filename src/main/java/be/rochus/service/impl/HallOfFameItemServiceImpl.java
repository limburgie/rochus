package be.rochus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import be.rochus.domain.HallOfFameItem;
import be.rochus.domain.Schutter;
import be.rochus.domain.type.Achievement;
import be.rochus.repository.HallOfFameItemRepository;
import be.rochus.service.HallOfFameItemService;

@Named
@Transactional(readOnly=true)
public class HallOfFameItemServiceImpl implements HallOfFameItemService {

	@Inject private HallOfFameItemRepository repo;

	public List<HallOfFameItem> getItems() {
		return repo.findAllItems();
	}

	public HallOfFameItem getItem(Long id) {
		return repo.findOne(id);
	}

	@Transactional
	public void save(HallOfFameItem item) {
		repo.save(item);
	}

	@Transactional
	public void delete(HallOfFameItem item) {
		repo.delete(item);
	}

	public List<Achievement> getAchievements(Schutter schutter) {
		List<HallOfFameItem> items = repo.findByAchiever(schutter.getFullName());
		List<Achievement> achievements = new ArrayList<Achievement>();
		for(HallOfFameItem item: items) {
			Achievement achievement = new Achievement();
			achievement.setJaar(item.getJaar());
			achievement.setPrice(item.getPrijs());
			achievements.add(achievement);
		}
		return achievements;
	}
	
}
