package com.foodbox.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imgId;
	
	private String name;
	
	private String type;
	
	@Lob
	@Column(name = "imagedata", columnDefinition = "MEDIUMBLOB")
	private byte[] imageData;
	
	@OneToOne(mappedBy = "productImage")
	@JsonBackReference
	private Product product;

	public ProductImage() {
		super();
	}

	public ProductImage(Long imgId, String name, String type, byte[] imageData, Product product) {
		super();
		this.imgId = imgId;
		this.name = name;
		this.type = type;
		this.imageData = imageData;
		this.product = product;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
