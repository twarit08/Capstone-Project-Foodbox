package com.foodbox.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodbox.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

}
