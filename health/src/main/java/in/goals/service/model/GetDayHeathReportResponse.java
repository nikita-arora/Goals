package in.goals.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetDayHeathReportResponse extends BaseResponse {

	@JsonProperty("website_day_health")
	private DayHealthModel model;
}
