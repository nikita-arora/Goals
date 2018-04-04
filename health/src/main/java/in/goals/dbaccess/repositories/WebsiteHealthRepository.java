package in.goals.dbaccess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.goals.dbaccess.model.WebsiteHealthDAO;

@Repository
public interface WebsiteHealthRepository extends JpaRepository<WebsiteHealthDAO, Integer> {

}
