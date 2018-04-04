package in.goals.dbaccess.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.goals.dbaccess.model.WebsiteDAO;

@Repository
public interface WebsiteRepository extends JpaRepository<WebsiteDAO, Integer> {

	List<WebsiteDAO> findByActiveTrue();

}
