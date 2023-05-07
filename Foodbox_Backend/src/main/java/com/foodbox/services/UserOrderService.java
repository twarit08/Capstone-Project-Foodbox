package com.foodbox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.entities.ProductQuantity;
import com.foodbox.entities.UserOrder;
import com.foodbox.repo.OrderRepo;
import com.foodbox.repo.ProductQuantityRepo;

@Service
public class UserOrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ProductQuantityRepo productQuantityRepo;
	
	public UserOrder saveOrder(UserOrder userOrder) {
		UserOrder orderCreated = this.orderRepo.save(userOrder);
		return orderCreated;
	}
	
	public ProductQuantity saveProductQuantity(ProductQuantity productQuantity) {
		ProductQuantity product = this.productQuantityRepo.save(productQuantity);
		return product;
	}
	
	public List<UserOrder> getAllOrders(){
		return this.orderRepo.findAll();
	}
	
	public List<UserOrder> getOrderByUsername(String username){
		List<UserOrder> orders = this.orderRepo.findByUsername(username);
		return orders;
	}
	
	public UserOrder getOrderById(Long oid) {
		UserOrder order = this.orderRepo.findById(oid).get();
		return order;
	}
	
	public void deleteOrderById(Long oid) {
		this.orderRepo.deleteById(oid);
	}

}
