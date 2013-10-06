package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;

import be.rochus.domain.Wedstrijd;
import be.rochus.faces.FacesUtil;
import be.rochus.service.WedstrijdService;

@Named @Scope("view")
public class WedstrijdBean implements Serializable {

	@Inject private AccountBean accountBean;
	@Inject private WedstrijdService wedstrijdService;
	@Inject private FacesUtil facesUtil;
	
	private List<Wedstrijd> wedstrijden;
	private Wedstrijd wedstrijd;
	private Date time;
	private boolean showPastGames;
	
	@PostConstruct
	public void init() {
		String wedstrijdId = facesUtil.getRequestParam("id");
		if (wedstrijdId != null) {
			setCurrentWedstrijd(Long.valueOf(wedstrijdId));
		} else {
			prepareNew();
		}
	}

	public void prepareNew() {
		wedstrijd = new Wedstrijd();
	}
	
	private void updateTime() {
		if(wedstrijd.getDate() == null) {
			return;
		}
		DateTime d = new DateTime(wedstrijd.getDate());
		DateTime t = new DateTime(time);
		d = d.withHourOfDay(t.getHourOfDay()).withMinuteOfHour(t.getMinuteOfHour());
		wedstrijd.setDate(d.toDate());
	}
	
	public void save() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
		}
		updateTime();
		wedstrijdService.save(wedstrijd);
		facesUtil.closeDialog();
		retrieveGames();
		facesUtil.info("Wedstrijd werd succesvol opgeslagen!");
	}
	
	public void delete() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
		}
		wedstrijdService.delete(wedstrijd);
		retrieveGames();
		facesUtil.info("Wedstrijd werd succesvol verwijderd!");
	}

	public List<Wedstrijd> getWedstrijden() {
		if(wedstrijden == null) {
			retrieveGames();
		}
		return wedstrijden;
	}
	
	public void setCurrentWedstrijd(long id) {
		wedstrijd = wedstrijdService.getWedstrijd(id);
		time = wedstrijd.getDate();
	}

	public void retrieveGames() {
		if(showPastGames) {
			wedstrijden = wedstrijdService.getWedstrijden();
		} else {
			wedstrijden = wedstrijdService.getUpcoming();
		}
	}
	
	public boolean isShowPastGames() {
		return showPastGames;
	}

	public void setShowPastGames(boolean showPastGames) {
		this.showPastGames = showPastGames;
	}

	public Wedstrijd getWedstrijd() {
		return wedstrijd;
	}
	
	public void setWedstrijd(Wedstrijd wedstrijd) {
		this.wedstrijd = wedstrijd;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
