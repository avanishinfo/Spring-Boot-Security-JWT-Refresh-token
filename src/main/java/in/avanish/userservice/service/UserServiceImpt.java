package in.avanish.userservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.avanish.userservice.domain.Role;
import in.avanish.userservice.domain.User;
import in.avanish.userservice.repo.RoleRepo;
import in.avanish.userservice.repo.UserRepo;

@Service
@Transactional
public class UserServiceImpt implements UserService, UserDetailsService{

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	
	public UserServiceImpt(UserRepo userRepo, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public User getUser(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		
		user.getRoles().add(role);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("user not found in the DB");
		}else {
			
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role-> authorities.add(new SimpleGrantedAuthority(role.getName())));
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
	}

}
