package in.avanish.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.avanish.userservice.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
