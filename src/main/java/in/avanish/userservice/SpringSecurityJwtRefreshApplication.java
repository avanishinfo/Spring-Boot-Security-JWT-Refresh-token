package in.avanish.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import in.avanish.userservice.domain.Role;
import in.avanish.userservice.domain.User;
import in.avanish.userservice.service.UserService;

@SpringBootApplication
public class SpringSecurityJwtRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtRefreshApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Avanish Singh", "avanishinfo", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Mayank Gupta", "mayank007", "2345", new ArrayList<>()));
			userService.saveUser(new User(null, "Deep Prakash", "deep043", "3456", new ArrayList<>()));
			userService.saveUser(new User(null, "Surojit Ghos", "surojit", "1234", new ArrayList<>()));

			userService.addRoleToUser("avanishinfo", "ROLE_USER");
			userService.addRoleToUser("avanishinfo", "ROLE_MANAGER");
			userService.addRoleToUser("mayank007", "ROLE_MANAGER");
			userService.addRoleToUser("deep043", "ROLE_ADMIN");
			userService.addRoleToUser("surojit", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("surojit", "ROLE_ADMIN");
			userService.addRoleToUser("surojit", "ROLE_USER");
		};
	}
}
