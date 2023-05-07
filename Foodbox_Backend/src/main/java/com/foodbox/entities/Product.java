package com.foodbox.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	@NotBlank(message = "name can't be blank!")
	private String name;
	
	@NotBlank(message = "recommendations can't be blank!")
	private String recommendation;
	
	@NotNull(message = "price can't be null!")
	private Double price;
	
	@NotBlank(message = "cuisine can't be blank!")
	private String cuisineType;
	
	@NotBlank(message = "type can't be blank!")
	private String subType;
	
	@NotBlank(message="description can't be blank!")
	private String description;
	
	@NotBlank(message = "diet type can't be blank!")
	private String dietType;
	
	@NotNull(message = "is available can't be null!")
	private boolean isAvailable;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private ProductImage productImage;

	public Product() {
		super();
	}

	public Product(Long pid, String name, String recommendation, Double price, String cuisineType, String subType,
			String description, String dietType, boolean isAvailable, ProductImage productImage) {
		super();
		this.pid = pid;
		this.name = name;
		this.recommendation = recommendation;
		this.price = price;
		this.cuisineType = cuisineType;
		this.subType = subType;
		this.description = description;
		this.dietType = dietType;
		this.isAvailable = isAvailable;
		this.productImage = productImage;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDietType() {
		return dietType;
	}

	public void setDietType(String dietType) {
		this.dietType = dietType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public ProductImage getProductImage() {
		return productImage;
	}

	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}
	
}
