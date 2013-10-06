package be.rochus.faces.bean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import be.rochus.faces.FacesUtil;

@Named @Scope("session")
public class AccountBean implements Serializable {

	@Inject private FacesUtil facesUtil;
	
	private boolean loggedIn;
	private String password;
	
	public String login() {
		if("winnie".equals(password)) {
			loggedIn = true;
			return "/pages/home.xhtml?faces-redirect=true";
		} else {
			facesUtil.error("Wachtwoord is niet correct");
			return null;
		}
	}
	
	public String logout() {
		loggedIn = false;
		return "/pages/home.xhtml?faces-redirect=true";
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
