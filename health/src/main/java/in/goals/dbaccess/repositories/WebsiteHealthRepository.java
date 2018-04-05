package in.goals.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.goals.dbaccess.model.WebsiteHealthDAO;

@Repository
public interface WebsiteHealthRepository extends JpaRepository<WebsiteHealthDAO, Integer> {

	List<WebsiteHealthDAO> findByWebsite_ActiveTrueAndCreatedAtBetween(Date startDate, Date endDate);
}
