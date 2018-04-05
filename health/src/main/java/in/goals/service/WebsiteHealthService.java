package in.goals.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	public List<WebsiteHealthDAO> getWeeklyReport() {

		Calendar cal = Calendar.getInstance();
		Date today = new Date(cal.getTimeInMillis());

		cal.add(Calendar.DATE, -7);
		Date startDate = new Date(cal.getTimeInMillis());
	}
}
