package com.foodbox.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ProductQuantity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pqid;
	
	@OneToOne
	private Product product;
	
	private int quantity;

	public ProductQuantity() {
		super();
	}

	public ProductQuantity(Long pqid, Product product, int quantity) {
		super();
		this.pqid = pqid;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getPqid() {
		return pqid;
	}

	public void setPqid(Long pqid) {
		this.pqid = pqid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
