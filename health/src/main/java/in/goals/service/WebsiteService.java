package in.goals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.goals.dbaccess.model.WebsiteDAO;
import in.goals.dbaccess.repositories.WebsiteRepository;

@Service
public class WebsiteService {

	@Autowired
	WebsiteRepository websiteRepository;

	public WebsiteDAO createWebsite(WebsiteDAO website) {
		return websiteRepository.save(website);
	}

	public List<WebsiteDAO> getAllWebsites() {
		return websiteRepository.findAll();
	}

	public List<WebsiteDAO> getAllActiveWebsites() {
		return websiteRepository.findByActiveTrue();
	}

}
