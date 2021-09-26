package in.avanish.userservice.service;

import java.util.List;

import in.avanish.userservice.domain.Role;
import in.avanish.userservice.domain.User;

public interface UserService {

	User saveUser(User user);
	Role saveRole(Role role);
	User getUser(String username);
	List<User> getUsers();
	void addRoleToUser(String username, String roleName);

}
