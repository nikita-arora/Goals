package in.goals.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetWebsiteHealthReportResponse extends BaseResponse {

	@JsonProperty("website_health")
	private WebsiteHealthModel model;

}
