package com.foodbox.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodbox.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	
	public List<Product> findByNameContainingIgnoreCase(String name);
	public List<Product> findByCuisineType(String cuisineType);
}
