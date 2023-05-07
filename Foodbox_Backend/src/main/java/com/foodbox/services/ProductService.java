package com.foodbox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.entities.Product;
import com.foodbox.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	//add product
	public Product addProduct(Product product) {
		Product newProduct = this.productRepo.save(product);
		return newProduct;
	}
	
	//update product
	public void updateProduct(Long id, Product product) {
		Product updateProduct = this.productRepo.findById(id).get();
		updateProduct.setName(product.getName());
		updateProduct.setRecommendation(product.getRecommendation());
		updateProduct.setPrice(product.getPrice());
		updateProduct.setCuisineType(product.getCuisineType());
		updateProduct.setSubType(product.getSubType());
		updateProduct.setDescription(product.getDescription());
		updateProduct.setDietType(product.getDietType());
		this.productRepo.save(updateProduct);
	}
	
	//get product by id
	public Product getProductById(Long pid) {
		Product product = this.productRepo.findById(pid).get();
		return product;
	}
	
	//set availability
	public void setAvailability(Long pid, Product product) {
		Product updateProduct = this.productRepo.findById(pid).get();
		updateProduct.setAvailable(product.isAvailable());
		this.productRepo.save(updateProduct);
	}
	
	//get all products
	public List<Product> getAllProducts(){
		List<Product> allProducts = this.productRepo.findAll();
		return allProducts;
	}
	
	//find by name
	public List<Product> getAllByName(String name){
		return this.productRepo.findByNameContainingIgnoreCase(name);
	}
	
	//find by category
	public List<Product> getAllByCuisine(String cuisineType){
		return this.productRepo.findByCuisineType(cuisineType);
	}
	
	//delete by id
	public void deleteProductById(Long pid) {
		this.productRepo.deleteById(pid);
	}

}
