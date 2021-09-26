package in.avanish.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.avanish.userservice.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
