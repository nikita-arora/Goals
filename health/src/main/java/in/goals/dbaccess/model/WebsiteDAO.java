package in.goals.dbaccess.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "Websites")
@NamedQuery(name = "Websites.findAll", query = "SELECT w FROM WebsiteDAO w")
public class WebsiteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	@JsonProperty("id")
	private Integer id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String url;

	@Type(type = "yes_no")
	@Column(nullable = false)
	private boolean active;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("updated_at")
	private Date updatedAt;

	public WebsiteDAO() {

	}

	public WebsiteDAO(Integer id) {
		this.id = id;
	}

	public WebsiteDAO(String name) {
		this.name = name;
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
