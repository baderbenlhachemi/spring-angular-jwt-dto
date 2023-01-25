package ma.emsi.repository;

import java.util.Optional;

import ma.emsi.models.ERole;
import ma.emsi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
