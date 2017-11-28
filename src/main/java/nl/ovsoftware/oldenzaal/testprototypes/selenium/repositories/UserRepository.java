package nl.ovsoftware.oldenzaal.testprototypes.selenium.repositories;

import nl.ovsoftware.oldenzaal.testprototypes.selenium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
