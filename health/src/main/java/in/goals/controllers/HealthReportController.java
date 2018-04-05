package in.goals.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.goals.constants.GoalsUrl;
import in.goals.service.WebsiteHealthService;
import in.goals.service.model.GetAllWebsitesResponse;

@Controller
@RequestMapping(GoalsUrl.HEALTH_URL)
public class HealthReportController {

	@Autowired
	WebsiteHealthService healthService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<GetAllWebsitesResponse> getWebsiteReport() {
		GetAllWebsitesResponse res = new GetAllWebsitesResponse();
		try {
			// List<WebsiteDAO> websites = websiteService.getAllActiveWebsites();
			// res.setWebsiteList(websites);
			// res.setSuccess(true);
			return new ResponseEntity<GetAllWebsitesResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			res.setErrors(errors);
			res.setSuccess(false);
			ResponseEntity.BodyBuilder resEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ResponseEntity<GetAllWebsitesResponse> response = resEntity.body(res);
			return response;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GetAllWebsitesResponse> getWeeklyReport() {
		GetAllWebsitesResponse res = new GetAllWebsitesResponse();
		try {
			// List<WebsiteDAO> websites = websiteService.getAllActiveWebsites();
			// res.setWebsiteList(websites);
			// res.setSuccess(true);
			return new ResponseEntity<GetAllWebsitesResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			res.setErrors(errors);
			res.setSuccess(false);
			ResponseEntity.BodyBuilder resEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ResponseEntity<GetAllWebsitesResponse> response = resEntity.body(res);
			return response;
		}
	}

}
