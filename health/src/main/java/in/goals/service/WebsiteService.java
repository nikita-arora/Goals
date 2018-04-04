package in.goals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.goals.dbaccess.model.WebsiteDAO;
import in.goals.dbaccess.repositories.WebsiteRepository;
import lombok.NonNull;

@Service
public class WebsiteService {

	@Autowired
	WebsiteRepository websiteRepository;

	public WebsiteDAO createWebsite(WebsiteDAO website) throws Exception {

		if (websiteRepository.findByNameOrUrl(website.getName(), website.getUrl()) != null) {
			throw new Exception("Duplicate website entry");
		}
		return websiteRepository.save(website);
	}

	public WebsiteDAO deactivateWebsite(@NonNull Integer id) throws Exception {

		WebsiteDAO update = websiteRepository.findOne(id);
		if (update == null) {
			throw new Exception("No website found with id");
		}

		if (!update.isActive()) {
			throw new Exception("Website is already inactive");
		}

		update.setActive(false);
		return websiteRepository.save(update);
	}

	public List<WebsiteDAO> getAllWebsites() {
		return websiteRepository.findAll();
	}

	public List<WebsiteDAO> getAllActiveWebsites() {
		return websiteRepository.findByActiveTrue();
	}

}
