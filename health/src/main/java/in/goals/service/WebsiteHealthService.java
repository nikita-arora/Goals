package in.goals.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.goals.dbaccess.model.WebsiteDAO;
import in.goals.dbaccess.model.WebsiteHealthDAO;
import in.goals.dbaccess.repositories.WebsiteHealthRepository;
import in.goals.dbaccess.repositories.WebsiteRepository;
import in.goals.service.model.DayHealthModel;
import in.goals.service.model.WebsiteDayHealth;
import in.goals.service.model.WebsiteHealthModel;

@Service
public class WebsiteHealthService {

	@Autowired
	private WebsiteHealthRepository websiteHealthRepository;

	@Autowired
	private WebsiteRepository websiteRepository;

	public WebsiteHealthDAO persist(WebsiteHealthDAO dao) {
		WebsiteDAO website = websiteRepository.findById(dao.getWebsite().getId());
		dao.setWebsite(website);
		dao.setWebsiteId(website.getId());

		return websiteHealthRepository.save(dao);
	}

	public WebsiteHealthModel getWebsiteHealth(@NotNull String website, Long startDate, Long endDate) throws Exception {
		Date inputStart = null;
		Date inputEnd = null;
		Calendar cal = Calendar.getInstance();

		if (startDate == null) {
			inputStart = new Date(cal.getTimeInMillis());
		} else {
			inputStart = new Date(startDate);
		}

		if (endDate == null) {
			cal.add(Calendar.DATE, 14);
			inputEnd = new Date(cal.getTimeInMillis());
		} else {
			inputEnd = new Date(endDate);
		}

		WebsiteDAO dao = websiteRepository.findByNameOrUrl(website, null);
		List<WebsiteHealthDAO> healthList = websiteHealthRepository
				.findByWebsiteAndWebsite_ActiveTrueAndCreatedAtBetween(dao, inputStart, inputEnd);
		WebsiteHealthModel model = createWebsiteHealthModel(healthList);
		model.setDateSet(TimeUnit.DAYS.convert((inputEnd.getTime() - inputStart.getTime()), TimeUnit.MILLISECONDS));

		return model;

	}

	private WebsiteHealthModel createWebsiteHealthModel(List<WebsiteHealthDAO> healthList) throws Exception {
		if (healthList == null || healthList.isEmpty()) {
			throw new Exception("Insufficient data to create report");
		}
		Integer checkCount = healthList.size();
		Double positiveCount = 0.0, negativeCount = 0.0;
		for (WebsiteHealthDAO dao : healthList) {
			if (dao.getOk()) {
				positiveCount++;
			} else {
				negativeCount++;
			}
		}

		WebsiteHealthModel model = new WebsiteHealthModel();
		model.setWebsite(healthList.get(0).getWebsite());

		model.setSuccessPercent(positiveCount / checkCount);
		model.setFailPercent(negativeCount / checkCount);
		model.setHealth(healthList);

		return model;
	}

	public DayHealthModel getDayHeath(Long date) throws Exception {
		Date today = null;
		Calendar cal = Calendar.getInstance();

		if (date == null) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			today = new Date(cal.getTimeInMillis());
		} else {
			today = new Date(date);
		}

		List<WebsiteDAO> websites = websiteRepository.findByActiveTrue();
		return new DayHealthModel(createWebsiteDayHealth(websites, today));
	}

	private List<WebsiteDayHealth> createWebsiteDayHealth(List<WebsiteDAO> websites, Date today) throws Exception {
		if (websites == null || websites.isEmpty()) {
			throw new Exception("Insufficient data to create report");
		}

		List<WebsiteDayHealth> list = new ArrayList<>();
		for (WebsiteDAO dao : websites) {
			WebsiteDayHealth dayHealth = new WebsiteDayHealth();
			dayHealth.setWebsite(dao);
			Double positiveCount = 0.0, negativeCount = 0.0;

			List<WebsiteHealthDAO> healthList = websiteHealthRepository.findByWebsiteAndCreatedAtAfter(dao, today);
			Integer checkCount = healthList.size();
			for (WebsiteHealthDAO h : healthList) {
				if (h.getOk()) {
					positiveCount++;
				} else {
					negativeCount++;
				}
			}

			dayHealth.setSuccessPercent(positiveCount / checkCount);
			dayHealth.setFailPercent(negativeCount / checkCount);
			dayHealth.setHealth(healthList);
			list.add(dayHealth);
		}

		return list;
	}

}
