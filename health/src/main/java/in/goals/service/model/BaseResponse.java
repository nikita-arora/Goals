package in.goals.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BaseResponse {

	@JsonProperty("errors")
	List<String> errors;

	@JsonProperty("success")
	Boolean success;

	public BaseResponse() {
	}

	public BaseResponse(boolean success) {
		this.success = success;
	}

}
