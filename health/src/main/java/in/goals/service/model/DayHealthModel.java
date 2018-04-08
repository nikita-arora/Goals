package in.goals.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DayHealthModel {

	@JsonProperty("website-day")
	private List<WebsiteDayHealth> websiteDayHealth;

	public DayHealthModel(List<WebsiteDayHealth> list) {
		this.websiteDayHealth = list;
	}

}
