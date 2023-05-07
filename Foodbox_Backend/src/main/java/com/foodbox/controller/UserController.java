package com.foodbox.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foodbox.entities.Role;
import com.foodbox.entities.User;
import com.foodbox.entities.UserRole;
import com.foodbox.services.UserRegistrationService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@PostMapping("/user/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
		Role role = new Role();
		role.setRoleId(102L);
		role.setRoleName("CUSTOMER");
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userRole);
		
		if(this.userRegistrationService.getUserByUsername(user.getUsername())!= null) {
			System.out.println("Username already exist!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}else {
			User newUser = this.userRegistrationService.registerUser(user, userRoles);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getUserId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
	
	@PostConstruct
	public void createAdmin() {
		User admin = new User();
		admin.setUsername("admin@foodbox.com");
		admin.setFirstName("Twarit");
		admin.setLastName("Soni");
		admin.setContactNumber("6265857788");
		admin.setPassword("admin12345");
		Role role = new Role();
		role.setRoleId(101L);
		role.setRoleName("ADMIN");
		UserRole userRole = new UserRole();
		userRole.setUser(admin);
		userRole.setRole(role);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userRole);
		User createAdmin = this.userRegistrationService.registerUser(admin, userRoles);
		System.out.println("Admin Username: "+createAdmin.getUsername());
	}

}
