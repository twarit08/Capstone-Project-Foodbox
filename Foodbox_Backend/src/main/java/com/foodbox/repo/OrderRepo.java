package com.foodbox.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodbox.entities.UserOrder;

@Repository
public interface OrderRepo extends JpaRepository<UserOrder, Long>{
	public List<UserOrder> findByUsername(String username);
}
