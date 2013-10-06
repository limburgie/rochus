package be.rochus.service;

import java.util.List;

import be.rochus.domain.HallOfFameItem;
import be.rochus.domain.Schutter;
import be.rochus.domain.type.Achievement;

public interface HallOfFameItemService {

	List<HallOfFameItem> getItems();

	HallOfFameItem getItem(Long valueOf);

	void save(HallOfFameItem item);

	void delete(HallOfFameItem item);
	
	List<Achievement> getAchievements(Schutter schutter);

}
