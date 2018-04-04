package in.goals.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.goals.constants.GoalsUrl;
import in.goals.dbaccess.model.WebsiteDAO;
import in.goals.service.WebsiteService;
import in.goals.service.model.CreateWebsiteRequest;
import in.goals.service.model.CreateWebsiteResponse;
import in.goals.service.model.GetAllWebsitesResponse;
import in.goals.service.model.UpdateWebsiteResponse;

@RestController
@RequestMapping(GoalsUrl.WEBSITE_URL)
public class WebsiteController {

	@Autowired
	WebsiteService websiteService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CreateWebsiteResponse> createWebsite(@RequestBody CreateWebsiteRequest request) {
		CreateWebsiteResponse resp = new CreateWebsiteResponse();
		WebsiteDAO website;
		try {
			website = websiteService.createWebsite(request.getWebsite());
			resp.setWebsite(website);
			resp.setSuccess(true);
			return new ResponseEntity<CreateWebsiteResponse>(resp, HttpStatus.OK);
		} catch (Exception e) {
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			resp.setErrors(errors);
			resp.setSuccess(false);
			ResponseEntity.BodyBuilder resEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ResponseEntity<CreateWebsiteResponse> response = resEntity.body(resp);
			return response;
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/deactivate")
	public ResponseEntity<UpdateWebsiteResponse> deactivateWebsite(@PathVariable Integer id) {
		UpdateWebsiteResponse resp = new UpdateWebsiteResponse();
		WebsiteDAO website;
		try {
			website = websiteService.deactivateWebsite(id);
			resp.setWebsite(website);
			resp.setSuccess(true);
			return new ResponseEntity<UpdateWebsiteResponse>(resp, HttpStatus.OK);

		} catch (Exception e) {
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			resp.setErrors(errors);
			resp.setSuccess(false);
			ResponseEntity.BodyBuilder resEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ResponseEntity<UpdateWebsiteResponse> response = resEntity.body(resp);
			return response;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GetAllWebsitesResponse> getQuizzes() {
		GetAllWebsitesResponse res = new GetAllWebsitesResponse();
		try {
			List<WebsiteDAO> websites = websiteService.getAllActiveWebsites();
			res.setWebsiteList(websites);
			res.setSuccess(true);
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
