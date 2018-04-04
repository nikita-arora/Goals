package in.goals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.goals.dbaccess.model.WebsiteHealthDAO;
import in.goals.dbaccess.repositories.WebsiteHealthRepository;

@Service
public class WebsiteHealthService {

	@Autowired
	private WebsiteHealthRepository websiteHealthRepository;

	public WebsiteHealthDAO persist(WebsiteHealthDAO dao) {
		return websiteHealthRepository.save(dao);
	}
}
