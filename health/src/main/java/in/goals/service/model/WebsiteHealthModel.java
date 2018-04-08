package in.goals.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import in.goals.dbaccess.model.WebsiteDAO;
import in.goals.dbaccess.model.WebsiteHealthDAO;
import lombok.Data;

@Data
public class WebsiteHealthModel {

	@JsonProperty("website")
	private WebsiteDAO website;

	@JsonProperty("success_percent")
	private Double successPercent;

	@JsonProperty("fail_percent")
	private Double failPercent;

	@JsonProperty("number_of_days")
	private Long dateSet;

	@JsonProperty("health_data")
	List<WebsiteHealthDAO> health;
}
