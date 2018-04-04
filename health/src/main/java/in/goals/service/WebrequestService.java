package in.goals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.goals.dbaccess.model.WebsiteDAO;
import in.goals.dbaccess.model.WebsiteHealthDAO;
import in.goals.events.HealthPersistenceEvent;

@Service
public class WebrequestService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private WebsiteService websiteService;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Scheduled(fixedDelay = 300000)
	public void checkAllWebsites() {

		WebsiteHealthDAO websiteHealth = null;

		List<WebsiteDAO> list = websiteService.getAllActiveWebsites();
		for (WebsiteDAO web : list) {
			ResponseEntity<String> res = restTemplate.getForEntity(web.getUrl(), String.class);
			Boolean up = false;

			if (res.getStatusCode().is2xxSuccessful() || res.getStatusCode().is3xxRedirection()) {
				up = true;
			}

			websiteHealth = new WebsiteHealthDAO(web.getId(), res.getStatusCodeValue(), up);
			HealthPersistenceEvent persistenceEvent = new HealthPersistenceEvent(this, websiteHealth);
			eventPublisher.publishEvent(persistenceEvent);
		}

	}

}
