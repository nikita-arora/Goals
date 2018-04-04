package in.goals.service.model;

import in.goals.dbaccess.model.WebsiteDAO;
import lombok.Data;

@Data
public class CreateWebsiteRequest extends BaseRequest {

	private WebsiteDAO website;

}
