package in.goals.events;

import org.springframework.context.ApplicationEvent;

import in.goals.dbaccess.model.WebsiteHealthDAO;

public class HealthPersistenceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private WebsiteHealthDAO websiteHealthDAO = null;

	public HealthPersistenceEvent(Object source, WebsiteHealthDAO websiteHealth) {
		super(source);
		websiteHealthDAO = websiteHealth;
	}

	public WebsiteHealthDAO getWebsiteHealthDAO() {
		return websiteHealthDAO;
	}

}
