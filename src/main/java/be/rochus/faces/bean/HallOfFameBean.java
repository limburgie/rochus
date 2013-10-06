package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import be.rochus.domain.HallOfFameItem;
import be.rochus.faces.FacesUtil;
import be.rochus.service.HallOfFameItemService;

@Named @Scope("view")
public class HallOfFameBean implements Serializable {

	@Inject private HallOfFameItemService service;
	@Inject private FacesUtil facesUtil;
	@Inject private AccountBean accountBean;
	
	private List<HallOfFameItem> items;
	private HallOfFameItem item;
	
	@PostConstruct
	public void init() {
		String itemId = facesUtil.getRequestParam("itemId");
		if (itemId != null) {
			item = service.getItem(Long.valueOf(itemId));
		} else {
			prepareNew();
		}
	}
	
	public void prepareNew() {
		item = new HallOfFameItem();
	}
	
	public void save() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		service.save(item);
		initItems();
		facesUtil.info("Hall of fame item werd aangepast!");
		facesUtil.closeDialog();
	}
	
	public void delete() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		service.delete(item);
		initItems();
		facesUtil.info("Hall of fame item werd verwijderd!");
	}
	
	public void setCurrentItem(long itemId) {
		item = service.getItem(itemId);
	}
	
	public HallOfFameItem getItem() {
		return item;
	}

	public void setItem(HallOfFameItem item) {
		this.item = item;
	}

	public List<HallOfFameItem> getItems() {
		if(items == null) {
			initItems();
		}
		return items;
	}

	private void initItems() {
		items = service.getItems();
	}
	
}
