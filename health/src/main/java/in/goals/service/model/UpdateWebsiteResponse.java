package in.goals.service.model;

import in.goals.dbaccess.model.WebsiteDAO;
import lombok.Data;

@Data
public class UpdateWebsiteResponse extends BaseResponse {

	private WebsiteDAO website;

}
