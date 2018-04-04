package in.goals.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import in.goals.service.WebsiteHealthService;

@Component
public class HealthPersistenceListener implements ApplicationListener<HealthPersistenceEvent> {

	@Autowired
	WebsiteHealthService websiteHealthService;

	@Override
	public void onApplicationEvent(HealthPersistenceEvent event) {

		if (event.getWebsiteHealthDAO() != null) {
			websiteHealthService.persist(event.getWebsiteHealthDAO());
		}

	}

}
