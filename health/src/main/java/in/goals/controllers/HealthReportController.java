package in.goals.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.goals.constants.GoalsUrl;
import in.goals.service.WebsiteHealthService;
import in.goals.service.model.DayHealthModel;
import in.goals.service.model.GetDayHeathReportResponse;
import in.goals.service.model.GetWebsiteHealthReportResponse;
import in.goals.service.model.WebsiteHealthModel;

@Controller
@RequestMapping(GoalsUrl.HEALTH_URL)
public class HealthReportController {

	@Autowired
	WebsiteHealthService healthService;

	@RequestMapping(method = RequestMethod.GET, value = "/website/{name}")
	public ResponseEntity<GetWebsiteHealthReportResponse> getWebsiteReport(@PathVariable String name,
			@RequestParam(required = false) Long startDate, @RequestParam(required = false) Long endDate) {
		GetWebsiteHealthReportResponse res = new GetWebsiteHealthReportResponse();
		try {
			WebsiteHealthModel model = healthService.getWebsiteHealth(name, startDate, endDate);
			res.setModel(model);
			res.setSuccess(true);
			return new ResponseEntity<GetWebsiteHealthReportResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			res.setErrors(errors);
			res.setSuccess(false);
			ResponseEntity.BodyBuilder resEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ResponseEntity<GetWebsiteHealthReportResponse> response = resEntity.body(res);
			return response;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/day")
	public ResponseEntity<GetDayHeathReportResponse> getDayReport(@RequestParam(required = false) Long date) {
		GetDayHeathReportResponse res = new GetDayHeathReportResponse();
		try {
			DayHealthModel model = healthService.getDayHeath(date);
			res.setModel(model);
			res.setSuccess(true);
			return new ResponseEntity<GetDayHeathReportResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			res.setErrors(errors);
			res.setSuccess(false);
			ResponseEntity.BodyBuilder resEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ResponseEntity<GetDayHeathReportResponse> response = resEntity.body(res);
			return response;
		}
	}

}
