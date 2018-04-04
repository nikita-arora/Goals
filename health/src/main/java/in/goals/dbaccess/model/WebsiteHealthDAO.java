package in.goals.dbaccess.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "Website_Health")
public class WebsiteHealthDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotNull
	@JsonProperty("website")
	@ManyToOne
	@JoinColumn(name = "website_id", nullable = false)
	private WebsiteDAO website;

	@Column(name = "website_id", insertable = false, updatable = false)
	@JsonIgnore
	@Getter
	private Integer websiteId;

	@JsonProperty("created_at")
	@Type(type = "timestamp")
	private Date createdAt;

	@JsonProperty("updated_at")
	@Type(type = "timestamp")
	private Date updatedAt;

	@JsonProperty("status_code")
	private Integer statusCode;

	@JsonProperty("health_ok")
	@Type(type = "yes_no")
	@Column(nullable = false)
	private Boolean ok;

	public WebsiteHealthDAO() {

	}

	public WebsiteHealthDAO(Integer websiteId, Integer statusCode, Boolean healthOk) {
		this.website = new WebsiteDAO(websiteId);
		this.statusCode = statusCode;
		this.ok = healthOk;
	}

	@PrePersist
	void onCreate() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	@PreUpdate
	void onPersist() {
		this.setUpdatedAt(new Date());
	}

}
