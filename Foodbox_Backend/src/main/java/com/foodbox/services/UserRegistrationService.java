package com.foodbox.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodbox.entities.User;
import com.foodbox.entities.UserRole;
import com.foodbox.repo.RoleRepo;
import com.foodbox.repo.UserRepo;

@Service
public class UserRegistrationService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//register new user
	public User registerUser(User user, Set<UserRole> userRole) {
		User newUser = this.userRepo.findByUsername(user.getUsername());
		
		try {
			if(newUser!=null) {
				throw new Exception("Username already exist!");
			}else {
				//create new user
				
				for(UserRole uR: userRole) {
					this.roleRepo.save(uR.getRole());
				}
				
				//setting user roles
				user.getUserRole().addAll(userRole);
				
				//encoding password
				user.setPassword(this.passwordEncoder.encode(user.getPassword()));
				
				//saving new user
				newUser = this.userRepo.save(user);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("User is already created!");
			System.out.println(e);
		}
		return newUser;
	}
	
	public User getUserByUsername(String username) {
		User user = this.userRepo.findByUsername(username);
		return user;
	}
	
	

}
