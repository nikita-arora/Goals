package in.goals.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import in.goals.dbaccess.model.WebsiteDAO;
import lombok.Data;

@Data
public class GetAllWebsitesResponse extends BaseResponse {

	@JsonProperty("websites")
	List<WebsiteDAO> websiteList;
}
