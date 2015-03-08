package be.rochus.faces;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
public class FacesUtil {
	
	public void error(String msg, Exception e) {
		error(msg);
		e.printStackTrace();
	}
	
	public void error(String msg) {
		message(msg, FacesMessage.SEVERITY_ERROR);
	}
	
	public void info(String msg) {
		message(msg, FacesMessage.SEVERITY_INFO);
	}
	
	public String getRequestParam(String name) {
		return ec().getRequestParameterMap().get(name);
	}
	
	public void unexpectedError(Exception e) {
		error("Er is een onverwachte fout opgetreden. Contacteer de webmaster.");
		e.printStackTrace();
	}
	
	public String unauthorizedError() {
		error("Je mag deze actie niet uitvoeren. Gelieve eerst in te loggen.");
		return toHome();
	}
	
	public void closeDialog() {
		closeDialog("dialog");
	}
	
	public void closeDialog(String dialogName) {
		RequestContext.getCurrentInstance().execute("PF('"+dialogName+"').hide()");
	}
	
	public void redirect(String url) {
		try {
			if(url == null) {
				return;
			}
			if(!url.startsWith("http")) {
				url = getRequest().getContextPath() + url;
			}
			ec().redirect(url);
		} catch (IOException e) {
			unexpectedError(e);
		}
	}
	
	public String toHome() {
		return "/pages/home.xhtml?faces-redirect=true";
	}
	
	public String getContextPath() {
		return ec().getRequestContextPath();
	}
	
	public String getRealPath(String path) {
		return ec().getRealPath(path);
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) ec().getRequest();
	}

	private void message(String msg, Severity severity) {
		fc().addMessage(null, new FacesMessage(severity, msg, msg));
	}
	
	private ExternalContext ec() {
		return fc().getExternalContext();
	}
	
	private FacesContext fc() {
		return FacesContext.getCurrentInstance();
	}
	
}
